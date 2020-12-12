package com.sarathe.expense.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException {
        response.addHeader("WWW-Authenticate", "Basic realm=" +getRealmName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        if(authEx instanceof LockedException){
            writer.println("Your Account is locked");
        }else if (authEx instanceof BadCredentialsException){
            writer.println("Invalid Username/Password");
        }else if(authEx instanceof DisabledException){
            writer.println("Your account is not yet verified");
        }
        else{
            writer.println("Oops!! Something happened, but I'm not telling you what. ");
        }

    }

    @Override
    public void afterPropertiesSet()  {
        setRealmName("Sarathe");
        super.afterPropertiesSet();
    }
}
