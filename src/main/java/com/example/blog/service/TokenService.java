package com.example.blog.service;

import com.example.blog.config.jwt.TokenProvider;
import com.example.blog.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {   // String 자료형(문자열 리턴) createNewAccessToken 메서드(String 자료형 refreshToken 변수명)
        // 토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)) {          // tokenProvider 의 validToken 메서드(refreshToken 변수명)
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}