package com.korit.senicare.handler;


import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.korit.senicare.common.object.CustomOAuth2User;

import java.io.IOException;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// OAuth2 유저 서비스 작업 성공했을 때의 처리
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    
    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException, ServletException {

        CustomOAuth2User customOAuth2User = (CustomOAuth2User)authentication.getPrincipal();

        //String userId = customOAuth2User.getName();
        Map<String, Object> attributes = customOAuth2User.getAttributes();
        boolean existed = customOAuth2User.isExisted();

        // 회원가입 o
        if(existed) {
            String accessToken = (String)attributes.get("accessToken");
            response.sendRedirect("http://localhost:3000/sns-success?accessToken=" + accessToken + "&expiration=36000");
        } 
        // 회원가입 x
        else {
            String snsId = (String)attributes.get("snsId");
            String joinPath = (String)attributes.get("joinPath");
            response.sendRedirect("http://localhost:3000/auth?snsId="+ snsId + "&joinPath=" + joinPath);
            
        } 
        
    }
    
}
