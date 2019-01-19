package com.ascending.blair.extend.security;

import com.ascending.blair.domain.User;
import com.ascending.blair.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);


    @Override
    public UserDetails loadUserByUsername(String username){

        User domainUser = null;
        try {
            domainUser = userService.findByUsername(username);
        } catch (Exception repositoryProblem){
            logger.debug("catch AuthenticationServiceException from AuthenticationProvider");
        }

        return domainUser;
    }
}
