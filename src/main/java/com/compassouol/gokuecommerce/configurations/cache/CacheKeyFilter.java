package com.compassouol.gokuecommerce.configurations.cache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.compassouol.gokuecommerce.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CacheKeyFilter implements HandlerInterceptor {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        final CacheableKey cachingAnnotation = ((HandlerMethod) handler).getMethod()
                .getAnnotation((CacheableKey.class));

        final String requestTokenHeader = request.getHeader("Authorization");

        if (cachingAnnotation != null) {
            String jwtToken = requestTokenHeader.substring(7);
            if (jwtToken != null) {
                request.setAttribute("cacheableKey", jwtTokenProvider.getUsername(jwtToken));
            }
        }

        return true;
    }
}
