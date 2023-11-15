package com.example.interviewskeleton.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityFilter.class);
    private static final String SECRET_TOKEN = "such-secure-much-wow";

    private static List<AntPathRequestMatcher> excludedMatchers = new ArrayList<>();

    static {
        excludedMatchers.add(new AntPathRequestMatcher("/first/words/save", HttpMethod.POST.name()));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getHeader("X-Auth-Token").equals(SECRET_TOKEN)) {
            LOGGER.info("Request was authenticated.");
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return excludedMatchers.stream()
                .anyMatch(matcher -> matcher.matches(request));
    }

}