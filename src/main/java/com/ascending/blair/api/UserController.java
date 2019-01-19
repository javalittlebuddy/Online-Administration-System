package com.ascending.blair.api;

import com.ascending.blair.domain.User;
import com.ascending.blair.repository.UserRepository;
import com.ascending.blair.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = {"/api/users","/api/user"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier(BeanIds.AUTHENTICATION_MANAGER)
    private AuthenticationManager authenticationManager;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUserList(){
        logger.debug("list users");
        return userService.findAll(); // so far return an empty list, then connect logic
    }

    @RequestMapping(value = "/{Id}", method = RequestMethod.GET)
    public User findUserById(@PathVariable("Id") Long userId){
        logger.debug("id is: " + userId);
        return userService.findById(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User generateUser(@RequestBody User user){
        return userService.save(user);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"lastName"})
    public List<User> getUserByLastName(@RequestParam(value = "lastName") String lastName){
        logger.debug("last name is: " + lastName);
        return userService.findByLastName(lastName);
    }

}
