package com.createvision.sivilima.filter;

import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessControlFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");

        if (httpServletRequest.getHeader("Access-Control-Request-Method") != null
                && "OPTIONS".equals(httpServletRequest.getMethod())) {
            // CORS "pre-flight" request
            httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            //  response.addHeader("Access-Control-Allow-Headers", "Authorization");
            httpServletResponse.addHeader("Access-Control-Allow-Headers", "*");
            httpServletResponse.addHeader("Access-Control-Max-Age", "3600");
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
