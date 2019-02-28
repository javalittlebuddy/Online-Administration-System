package com.ascending.blair.repository;

import com.ascending.blair.domain.Department;
import com.ascending.blair.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    Optional<Department> findById(Long id);

    List<Department> findAll();

}
