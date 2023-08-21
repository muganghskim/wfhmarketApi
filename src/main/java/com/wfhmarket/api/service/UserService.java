package com.wfhmarket.api.service;

import com.wfhmarket.api.model.Member;
import com.wfhmarket.api.repository.UserRepository;
import com.wfhmarket.api.util.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    private final ReactiveAuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, ReactiveAuthenticationManager authenticationManager)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    // 회원가입 : 아이디와 패스워드 닉네임만 저장
    // todo : 추가정보 처리 ex: role
    public Mono<Member> signUp(String userEmail, String password, String username) {
        // 중복 회원 확인
        return userRepository.findByEmail(userEmail)
                .flatMap(existingMember -> {
                    return Mono.<Member>error(new RuntimeException("이미 가입되어 있는 회원입니다."));
                })
                .switchIfEmpty(Mono.defer(() -> {
                    String encodedPassword = passwordEncoder.encode(password);
                    Member member = Member.builder()
                            .email(userEmail)
                            .password(encodedPassword)
                            .name(username)
                            .build();
                    return userRepository.save(member);
                }));
    }



    // 로그인
    public Mono<Map<String, Object>> login(String userEmail, String password) {
        return Mono.defer(() -> {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userEmail, password);
                    return authenticationManager.authenticate(authToken);
                })
                .doOnNext(authentication -> {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                })
                .flatMap(authentication -> {
                    String token = jwtProvider.generateToken(authentication);
                    String refreshToken = jwtProvider.generateRefreshToken(authentication);
                    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                    List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
                    boolean isAdmin = roles.contains("ROLE_ADMIN");

                    String username = userDetails.getUsername();

                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("token", token);
                    resultMap.put("refreshToken", refreshToken);
                    resultMap.put("username", username);
                    resultMap.put("userEmail", userEmail);

                    return Mono.just(resultMap);
                });
    }

    // 프로필 관리
    public Mono<Member> getProfile(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .switchIfEmpty(Mono.error(new RuntimeException("회원을 찾을 수 없습니다.")));
    }

    // 프로필 업데이트
    public Mono<Member> updateProfile(String userEmail, String newUsername, String newPassword) {
        return userRepository.findByEmail(userEmail)
                .switchIfEmpty(Mono.error(new RuntimeException("회원을 찾을 수 없습니다.")))
                .flatMap(member -> {
                    if (newUsername != null) {
                        member.setName(newUsername);
                    }
                    if (newPassword != null) {
                        String encodedPassword = passwordEncoder.encode(newPassword);
                        member.setPassword(encodedPassword);
                    }

                    return userRepository.save(member);
                });
    }

    // 프로필 삭제
    public Mono<Void> deleteProfile(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .switchIfEmpty(Mono.error(new RuntimeException("회원을 찾을 수 없습니다.")))
                .flatMap(member -> userRepository.delete(member));
    }

    // 리프레쉬 토큰으로 액세스 토큰 업데이트
    public Mono<String> updateAccessToken(String refreshToken) {
        if (refreshToken == null) {
            throw new IllegalArgumentException("Refresh Token is missing");
        }

        return jwtProvider.updateAccessToken(refreshToken);
    }




}
