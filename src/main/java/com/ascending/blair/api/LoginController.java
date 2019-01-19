package com.ascending.blair.api;

import com.ascending.blair.domain.User;
import com.ascending.blair.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@ResponseBody
@RequestMapping(value = {"/api/users/login", "/api/user/login"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(getClass());


//    @Autowired
//    private UserService userService;

    @Autowired
    @Qualifier(BeanIds.AUTHENTICATION_MANAGER)
    private AuthenticationManager authenticationManager;


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public User loginInfo(@RequestBody Map<String, String> json){
//        logger.debug("Username is " + json.get("username") + ", password is " + json.get("password"));
//
//        return new User();

        try {
            Authentication notFullyAuthenticated = new UsernamePasswordAuthenticationToken(
                    json.get("username"),
                    json.get("password")
            );
            final Authentication authentication = authenticationManager.authenticate(notFullyAuthenticated);
        } catch (AuthenticationException ex){
            logger.debug("authentication failure, please check your password.\n" + "You entered Username is " + json.get("username") + ", password is " + json.get("password"));
        }
        return new User();
    }

}
