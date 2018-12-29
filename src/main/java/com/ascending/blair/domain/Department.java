package com.ascending.blair.domain;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departments_id_seq")
    @SequenceGenerator(name = "departments_id_seq", sequenceName = "departments_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "department_name")
    private String departmentName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = CascadeType.ALL)
    private List<User> user;

    public Long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
