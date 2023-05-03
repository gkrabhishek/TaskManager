package com.project.taskmanagerapp.JWT;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Security;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    Claims claims=null;
    private  String userName=null;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("do filter");
        if(httpServletRequest.getServletPath().matches("/user/login|/user/signup")){
            System.out.println("inside dofiler in JwtFilter");
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
        else{
            String authorizationHeader= httpServletRequest.getHeader("Authorization");
            System.out.println("auth spring"+authorizationHeader);
            String token=null;
            if(authorizationHeader!=null&&authorizationHeader.startsWith("Bearer "))
            {
                token=authorizationHeader.substring(7);
                userName=jwtUtil.extractUsername(token);
                claims=jwtUtil.extractAllClaims(token);
            }
            if(userName!=null&& SecurityContextHolder.getContext().getAuthentication()==null)
            {
                UserDetails userDetails=customUserDetailsService.loadUserByUsername(userName);
                if(jwtUtil.validateToken(token,userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            System.out.println("send....");
                filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }
    public boolean isManager(){
        return "manager".equalsIgnoreCase((String)claims.get("role"));
    }
    public boolean isUser(){
        return "user".equalsIgnoreCase((String)claims.get("role"));
    }
    public String getCurrentUser(){
        return userName;
    }
}
