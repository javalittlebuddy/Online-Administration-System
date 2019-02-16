package com.ascending.blair.service;

import com.ascending.blair.domain.Authority;
import com.ascending.blair.domain.User;
import com.ascending.blair.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    //User user = new User(userId);
    @Transactional
    public List<Authority> findByUser(User user){
        return authorityRepository.findByUser_Id(user.getId());
    }
}
