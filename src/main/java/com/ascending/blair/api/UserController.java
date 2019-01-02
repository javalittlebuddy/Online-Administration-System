package com.ascending.blair.api;

import com.ascending.blair.domain.User;
import com.ascending.blair.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUserList(){
        logger.debug("list users");
        return userRepository.findAll(); // so far return an empty list, then connect logic
    }

    @RequestMapping(value = "/{Id}", method = RequestMethod.GET)
    public User findUserById(@PathVariable("Id") Long userId){
        logger.debug("id is: " + userId);
        return userRepository.findById(userId).get();
    }

}
