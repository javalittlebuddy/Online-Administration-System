package com.ascending.blair.service;

import com.ascending.blair.domain.User;
import com.ascending.blair.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

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
    public User findByUsername(String username) throws NotFoundException {

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

//    public User createUser(User user){
//
//    }
}
