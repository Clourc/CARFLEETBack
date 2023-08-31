package com.project.carfleet.filter;

import com.project.carfleet.jwt.JwtUtil;
import com.project.carfleet.jwt.UserPrincipal;
import com.project.carfleet.service.UserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtRequestFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService){
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        System.out.println("authorizationHeader " + authorizationHeader);

        String CP = null;
        String token = null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            token = authorizationHeader.substring(7);
            CP = jwtUtil.extractCP(token);
        }

        if(CP != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserPrincipal userPrincipal = this.userDetailsService.loadUserByCP(CP);
            System.out.println("jwtUtil validateToken " + jwtUtil.validateToken(token, userPrincipal));
            System.out.println("userPrincipal authorities " + userPrincipal.getAuthorities());

            if(jwtUtil.validateToken(token, userPrincipal)){
                var usernamePasswordAuthentification = new UsernamePasswordAuthenticationToken(
                        userPrincipal,
                        null,
                        userPrincipal.getAuthorities()
                );
                usernamePasswordAuthentification.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                System.out.println("usernamePasswordAuthenticationToken " + usernamePasswordAuthentification);
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(usernamePasswordAuthentification);
            }
        }
        chain.doFilter(request, response);
    }
}
