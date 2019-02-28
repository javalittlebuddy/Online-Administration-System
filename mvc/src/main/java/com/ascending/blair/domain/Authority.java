package com.ascending.blair.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "authorities_id_seq")
    @SequenceGenerator(name = "authorities_id_seq", sequenceName = "authorities_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "role_name")
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    public Authority(){

    }

    public Authority(User user, String role){
        this.user = user;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
