package com.wfhmarket.api.util;

import com.wfhmarket.api.config.JwtConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class JwtAuthorizationFilter extends AuthenticationWebFilter {

    private JwtConfiguration jwtConfig;
    private ReactiveUserDetailsService userDetailsService;


    public JwtAuthorizationFilter(JwtConfiguration jwtConfig, ReactiveUserDetailsService reactiveUserDetailsService) {
        super(new NoopAuthenticationManager());
        this.jwtConfig = jwtConfig;
        this.userDetailsService = reactiveUserDetailsService;
        setServerAuthenticationConverter(new JwtAuthenticationConverter());
    }

    private class JwtAuthenticationConverter implements ServerAuthenticationConverter {

        @Override
        public Mono<Authentication> convert(ServerWebExchange exchange) {
            String header = exchange.getRequest().getHeaders().getFirst("Authorization");

            if (header == null || !header.startsWith("Bearer ")) {
                return Mono.empty();
            }

            String token = header.replace("Bearer ", "");

            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(jwtConfig.getSecret())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.getSubject();

                if (username != null && !username.isEmpty()) {
                    return userDetailsService.findByUsername(username)
                            .map(createAuthenticationFunction(exchange));
                }
            } catch (JwtException e) {
                // 토큰 파싱에 실패한 경우 로그를 남기고 인증 실패 처리
            }

            return Mono.empty();
        }

        private Function<UserDetails, AbstractAuthenticationToken> createAuthenticationFunction(ServerWebExchange exchange) {
            return userDetails -> new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
        }
    }
}

