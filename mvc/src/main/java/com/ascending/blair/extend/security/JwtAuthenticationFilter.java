package com.ascending.blair.extend.security;

import com.ascending.blair.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    private String tokenHeader = "TokenAuthorization";
    private String bear = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // TODO: 1. extract token from the first 7 digit
        //       2. get information from token: such as username
        //       3. authenticate user based on information in step 2; reference customer login

        String tokenHeader = request.getHeader(this.tokenHeader);

        if (tokenHeader != null && tokenHeader.startsWith(bear)){
            // 1. extract token from the first 7 digit
            String authToken = tokenHeader.substring(7);
            // 2. get information from token: such as username
            String username = jwtTokenUtil.getUsernameFromToken(authToken);

            logger.debug("checking authentication for user " + username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // 3. authenticate user based on information in step 2; reference customer login
                if (jwtTokenUtil.validateToken(authToken, userDetails)){
                    // token is valid, set authentication
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    logger.debug("authenticated user " + username + ", setting security context");

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    logger.debug("token is no longer valid");
                }
            }
        } else {
            logger.debug("token doesn't contain jwt bearer header");
        }
        filterChain.doFilter(request, response);

    }
}
