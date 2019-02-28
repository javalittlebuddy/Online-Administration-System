package com.ascending.blair.service;

import com.ascending.blair.config.AppConfig;
import com.ascending.blair.domain.Authority;
import com.ascending.blair.domain.User;
import com.ascending.blair.repository.AuthorityRepository;
import com.ascending.blair.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class AuthorityRepositoryTest {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void findRoleByUserIdTest(){

//        assertTrue(true);
        List<Authority> myList = new ArrayList<>();
        Authority auth1 = new Authority();
        Authority auth2 = new Authority();
        Authority auth3 = new Authority();
        User user1 = new User();
        User user2 = new User();

        auth1.setRole("ADMIN");
        auth2.setRole("USER");
        auth3.setRole("ADMIN");

        user1.setFirstName("Lily");
        user1.setLastName("Lee");
        user1.setEmail("ll@gmail.com");
        user1.setPassword("ll123");
        user1.setUsername("llily");

        user2.setFirstName("Silas");
        user2.setLastName("Black");
        user2.setEmail("sb@gmail.com");
        user2.setPassword("sb123");
        user2.setUsername("bsilas");
        auth2.setUser(user1);

        userRepository.save(user1);
        userRepository.save(user2);

        auth1.setUser(user1);
        auth2.setUser(user1);
        auth3.setUser(user1);

        authorityRepository.save(auth1);
        authorityRepository.save(auth2);

        myList.add(auth1);
        myList.add(auth2);

        List<Authority> expected = authorityRepository.findByUser_Id(user1.getId());
        assertEquals(myList, expected);
    }
}
