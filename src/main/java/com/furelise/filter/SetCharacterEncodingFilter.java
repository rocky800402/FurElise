package com.furelise.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(value = 2)
public class SetCharacterEncodingFilter extends OncePerRequestFilter {

    private String encoding = "UTF-8";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
//        
        request.setCharacterEncoding(encoding);
        filterChain.doFilter(request, response);
//        
    }
}
