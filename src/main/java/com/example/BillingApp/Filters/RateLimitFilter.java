package com.example.BillingApp.Filters;

import com.example.BillingApp.Models.AuthUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final Map<String, Deque<Long>> userRequests = new ConcurrentHashMap<>();
    private static final int LIMIT = 5;
    private static final long WINDOW_MS = 60_000;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws IOException, ServletException {

        AuthUser user = (AuthUser) request.getAttribute("authUser");
        if (user == null) {
            filterChain.doFilter(request, response);
            return;
        }

        long now = System.currentTimeMillis();
        userRequests.putIfAbsent(user.getUserId(), new ArrayDeque<>());

        Deque<Long> timestamps = userRequests.get(user.getUserId());
        while (!timestamps.isEmpty() && now - timestamps.peek() > WINDOW_MS) {
            timestamps.poll();
        }

        if (timestamps.size() >= LIMIT) {
            response.setStatus(429);
            response.getWriter().write("""
            {
              "error": "RATE_LIMIT_EXCEEDED",
              "message": "Too many requests. Please try again later."
            }
            """);
            return;
        }

        timestamps.add(now);
        filterChain.doFilter(request, response);
    }
}

