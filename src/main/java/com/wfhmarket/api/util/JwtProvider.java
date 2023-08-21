package com.wfhmarket.api.util;

import com.wfhmarket.api.config.JwtConfiguration;
import com.wfhmarket.api.model.Member;
import com.wfhmarket.api.repository.UserRepository;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;


@Component
public class JwtProvider {
    private final JwtConfiguration jwtConfiguration;
    private final UserRepository userRepository;

    private final ReactiveUserDetailsService userDetailsService;
    private final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    public JwtProvider(JwtConfiguration jwtConfiguration, UserRepository userRepository, ReactiveUserDetailsService userDetailsService) {
        this.jwtConfiguration = jwtConfiguration;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(Authentication authentication) {
        String userEmail = authentication.getName();
        Mono<Member> user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new UsernameNotFoundException("User with userEmail: " + userEmail + " not found");
        }
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        ZonedDateTime tokenExpiration = now.plusSeconds(jwtConfiguration.getTokenValidity());

        logger.info("userEmail: " + userEmail);
        logger.info("user: " + user);
        logger.info("now: " + now);
        logger.info("tokenExpiration: " + tokenExpiration);

        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenExpiration.toInstant()))
                .signWith(SignatureAlgorithm.HS512, jwtConfiguration.getSecret())
                .compact();
    }

    public String generateRefreshToken(Authentication authentication) {
        String userEmail = authentication.getName();
        Mono<Member> user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new UsernameNotFoundException("User with userEmail: " + userEmail + " not found");
        }
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        ZonedDateTime refreshTokenExpiration = now.plusSeconds(jwtConfiguration.getRefreshTokenValidity());

        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(refreshTokenExpiration.toInstant()))
                .signWith(SignatureAlgorithm.HS512, jwtConfiguration.getSecret())
                .compact();
    }

    public Mono<String> updateAccessToken(String refreshToken) {
        try {
            // 리프레시 토큰 확인
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtConfiguration.getSecret()).parseClaimsJws(refreshToken);

            // 해당 사용자에 대한 새로운 액세스 토큰 생성
            String userEmail = claims.getBody().getSubject();
            return userDetailsService.findByUsername(userEmail)
                    .flatMap(userDetails -> {
                        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        String newAccessToken = generateToken(authentication);
                        return Mono.just(newAccessToken);
                    });
        } catch (Exception e) {
            return Mono.error(e);
        }
    }


}
