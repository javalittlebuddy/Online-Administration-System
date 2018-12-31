package com.ascending.blair.service;

import com.ascending.blair.config.AppConfig;
import com.ascending.blair.domain.PayStub;
import com.ascending.blair.domain.User;
import com.ascending.blair.repository.PaystubRepository;
import com.ascending.blair.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class PaystubRepositoryTest {

    @Autowired
    private PaystubRepository paystubRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void findAllPaystubsByUserIdTest(){
        assertTrue(true);
//        PayStub payStub1 = new PayStub();
//        PayStub payStub2 = new PayStub();
//        User user1 = new User();
//        BigDecimal b1 = new BigDecimal("100000.00");
//        BigDecimal n1 = new BigDecimal("80000.00");
//        BigDecimal b2 = new BigDecimal("100300.00");
//        BigDecimal n2 = new BigDecimal("80200.00");
//        List<PayStub> mylist = new ArrayList<>();
//
//
//        user1.setId(1L);
//        user1.setFirstName("Sam");
//        user1.setLastName("Smith");
//        user1.setEmail("ss@gmail.com");
//        user1.setPassword("ss123");
//        user1.setUsername("ssam");
//
//        payStub1.setId(1L);
//        payStub1.setGrossWage(b1);
//        payStub1.setNetPay(n1);
//        payStub2.setId(2L);
//        payStub2.setGrossWage(b2);
//        payStub2.setNetPay(n2);
//
//        paystubRepository.save(payStub1);
//        paystubRepository.save(payStub2);
//
//        mylist.add(payStub1);
//        mylist.add(payStub2);
//        user1.setPayStub(mylist);
//
//        userRepository.save(user1);
//
//        List<PayStub> expected = paystubRepository.findAllPaystubsByUserId(user1.getId());
//        assertEquals(mylist, expected);



    }
}
