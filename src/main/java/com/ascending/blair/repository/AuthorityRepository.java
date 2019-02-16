package com.ascending.blair.repository;

import com.ascending.blair.domain.Authority;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {

//    @Query("select a from Authority a join fetch a.user where a.user.id = ?1")
//    List<Authority> findByUser_Id(Long id);

    List<Authority> findByUser_Id(Long id);
}
