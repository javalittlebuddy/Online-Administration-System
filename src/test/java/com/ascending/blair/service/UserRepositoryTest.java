package com.ascending.blair.service;

import com.ascending.blair.config.AppConfig;
import com.ascending.blair.domain.User;
import com.ascending.blair.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void findByLastnameAndFirstnameIgnoreCaseTest(){
//        assertTrue(false);
//        User user = userRepository.findByEmailIgnoreCase("ss@gmail.com");
        User user = new User();
        user.setId(1L);
        user.setFirstName("Sam");
        user.setLastName("Smith");
        user.setEmail("ss@gmail.com");
        user.setPassword("ss123");
        user.setUsername("ssam");
        userRepository.save(user);
        User expected = userRepository.findByLastnameAndFirstnameIgnoreCase(user.getFirstName(), user.getLastName());
        assertEquals(user.getId(),expected.getId());

    }

}
