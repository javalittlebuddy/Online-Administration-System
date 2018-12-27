package com.ascending.blair.domain;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;

@Configuration
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departments_id_seq")
    @SequenceGenerator(name = "departments_id_seq", sequenceName = "departments_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "user_id", unique = true)
    private Long userId;

    @Column(name = "department_name")
    private String departmentName;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
