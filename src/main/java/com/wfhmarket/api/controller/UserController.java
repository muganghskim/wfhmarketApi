package com.wfhmarket.api.controller;

import com.wfhmarket.api.model.Member;
import com.wfhmarket.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    // 회원가입
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Member> signUp(@RequestBody Mono<Member> signUpRequestMono) {
        return signUpRequestMono.flatMap(signUpRequest -> userService.signUp(signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getName()));
    }

    // 로그인
    @PostMapping("/login")
    public Mono<ResponseEntity<Map<String, Object>>> login(@RequestBody Mono<Member> loginRequestMono) {
        return loginRequestMono.flatMap(loginRequest -> {
            try {
                Mono<Map<String, Object>> userInfoMono = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
                return userInfoMono.map(userInfo -> {
                    log.info("토큰: " + userInfo.get("token"));
                    return ResponseEntity.ok(userInfo);
                }).onErrorResume(e -> {
                    log.error("Login error: " + e.getMessage(), e);
                    return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
                });
            } catch (Exception e) {
                log.error("Login error: " + e.getMessage(), e);
                return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
            }
        });
    }

    // 프로필 조회
    @GetMapping("/profile")
    public Mono<ResponseEntity<Member>> getProfile(@RequestParam String userEmail) {
        return userService.getProfile(userEmail)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }
    // todo : 프로필 업데이트 시 사용자 정보 추가 업데이트
    // 프로필 업데이트
    @PutMapping("/profile")
    public Mono<ResponseEntity<Member>> updateProfile(@RequestParam String userEmail, @RequestBody Mono<Member> updateProfileRequestMono) {
        return updateProfileRequestMono.flatMap(updateProfileRequest -> userService.updateProfile(userEmail, updateProfileRequest.getName(), updateProfileRequest.getPassword()))
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }


    // 회원탈퇴
    @PostMapping("/deleteProfile")
    public Mono<ResponseEntity<Void>> deleteProfile(@RequestParam String userEmail) {
        return userService.deleteProfile(userEmail)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }


    @PostMapping("/update-token")
    public Mono<ResponseEntity<Map<String, String>>> updateAccessToken(@RequestBody Map<String, String> tokenRequest) {
        String refreshToken = tokenRequest.get("refreshToken");

        return userService.updateAccessToken(refreshToken)
                .map(newAccessToken -> ResponseEntity.ok(Collections.singletonMap("accessToken", newAccessToken)))
                .onErrorResume(IllegalArgumentException.class, e -> Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Invalid Refresh Token"))))
                .onErrorResume(throwable -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Access Token Update Failed"))));
    }



}
