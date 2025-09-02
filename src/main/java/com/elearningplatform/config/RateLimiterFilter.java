package com.elearningplatform.config;


import com.elearningplatform.util.RateLimiter;
import io.github.bucket4j.Bucket;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class RateLimiterFilter implements Filter {

    @Autowired
    private RateLimiter rateLimiter;
    @Autowired
    private HttpServletResponse httpServletResponse;


    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {


        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null || !authentication.isAuthenticated()){
            chain.doFilter(request,response);
            return;
        }
        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        String userKey = username + "_" + httpRequest.getRemoteAddr();
        Bucket bucket = rateLimiter.resolveBucket(userKey, role);

        if(bucket.tryConsume(1)){
            chain.doFilter(request,response);
        }else{
            httpResponse.setStatus(429);
            httpResponse.getWriter().write("Rate limit exceeded, Please try again later");
        }

    }
}
