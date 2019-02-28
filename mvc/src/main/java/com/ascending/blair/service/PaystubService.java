package com.ascending.blair.service;

import com.ascending.blair.domain.PayStub;
import com.ascending.blair.repository.PaystubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaystubService {

    @Autowired
    private PaystubRepository paystubRepository;

    public PayStub save(PayStub payStub){
        return paystubRepository.save(payStub);
    }

    public List<PayStub> findAll(){
        return paystubRepository.findAll();
    }

    public List<PayStub> findAllPaystubsByUserId(Long userId){
        return paystubRepository.findAllPaystubsByUserId(userId);
    }
}
