package com.ascending.blair.repository;

import com.ascending.blair.domain.PayStub;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PaystubRepository extends CrudRepository<PayStub, Long> {

    Optional<PayStub> findById(Long id);

    List<PayStub> findAll();

    @Query("select p from PayStub p join fetch p.user where p.user.id = ?1")
    List<PayStub> findAllPaystubsByUserId(Long userId);

}
