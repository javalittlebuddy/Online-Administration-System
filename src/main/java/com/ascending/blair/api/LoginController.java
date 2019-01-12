package com.ascending.blair.api;

import com.ascending.blair.domain.User;
import com.ascending.blair.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@ResponseBody
@RequestMapping(value = {"/api/users/login", "/api/user/login"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(getClass());


//    @Autowired
//    private UserService userService;


    @RequestMapping(method = RequestMethod.POST)
    public User loginInfo(@RequestBody Map<String, String> json){
        logger.debug("Username is " + json.get("username") + ", password is " + json.get("password"));

        return new User();
    }

}
