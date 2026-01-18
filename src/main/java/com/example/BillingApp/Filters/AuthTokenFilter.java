package com.example.BillingApp.Filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("enter here");
        String token = request.getHeader("X-AUTH-TOKEN");

        if (token != null && token.contains(":")) {
            String[] parts = token.split(":");
            String userId = parts[0];
            String role = parts[1];
            System.out.println("userId"+userId);
            // Store in request context
            request.setAttribute("USER_ID", (String) userId);
            request.setAttribute("ROLE", (String) role);
        }

        filterChain.doFilter(request, response);
    }
}

