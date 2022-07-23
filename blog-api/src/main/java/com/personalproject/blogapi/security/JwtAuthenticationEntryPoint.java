package com.personalproject.blogapi.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//2nd step of jwt token(1st is dependency in  pom)
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    
    //commence will execute when an unauthorized person tries to access authorised api.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Access Denied!!");
    }
}
