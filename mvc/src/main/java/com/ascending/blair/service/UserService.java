package com.ascending.blair.service;

import com.ascending.blair.domain.Authority;
import com.ascending.blair.domain.User;
import com.ascending.blair.enumdef.AuthorityRole;
import com.ascending.blair.repository.AuthorityRepository;
import com.ascending.blair.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    public List<User> findAll(){
        //List<User> users = Lists.newArrayList(userRepository.findAll());
        return userRepository.findAll();
    }

    public User findById(Long Id){
        return userRepository.findById(Id).get();
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public List<User> findByLastName(String lastName){
        return userRepository.findByLastName(lastName);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {

        if (username == null || "".equals(username.trim())){
            throw new NullPointerException();
        }
        User user = userRepository.findByUsername(username);
//
//        if (user == null){
//            throw new NotFoundException();
//        }
        return user;
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public User createUser(User user){

        String encryptedPassword = passwordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setUsername(user.getUsername());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setPassword(user.getPassword());
        user.setEmail(user.getEmail());

        save(user);
        addAuthority(user, AuthorityRole.ROLE_REGISTERED_USER);
        save(user);

        return user;
    }

    public User addAuthority(User user, String role){
        Authority authority = new Authority();
        authority.setRole(role);
        authority.setUser(user);
        authorityRepository.save(authority);

        return userRepository.save(user);
    }
}
