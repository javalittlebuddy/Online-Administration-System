package com.ascending.blair.api;

import com.ascending.blair.domain.PayStub;
import com.ascending.blair.domain.User;
import com.ascending.blair.repository.PaystubRepository;
import com.ascending.blair.service.PaystubService;
import com.ascending.blair.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@Controller
//@ResponseBody
@RestController
@RequestMapping(value = {"/api/paystub","/api/paystubs"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class PayStubController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PaystubRepository paystubRepository;

    @Autowired
    private PaystubService paystubService;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/user/{Id}",method = RequestMethod.POST)
    public PayStub generatePaystubs(@RequestBody PayStub payStub, @PathVariable("Id") Long userId){
        User user = userService.findById(userId);
        payStub.setUser(user);
        paystubService.save(payStub);
        return payStub;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<PayStub> getPaystubList(){
        logger.debug("list paystubs");
        return paystubService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, params = {"userId"})
    public List<PayStub> findAllPaystubsByUserId(@RequestParam(value = "userId") Long userId){
        logger.debug("user id is " + userId);
        return paystubService.findAllPaystubsByUserId(userId);
    }
}
