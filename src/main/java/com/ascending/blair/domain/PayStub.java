package com.ascending.blair.domain;

import javax.persistence.*;

@Entity
@Table(name = "paystubs")
public class PayStub {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paystubs_id_seq")
    @SequenceGenerator(name = "paystubs_id_seq", sequenceName = "paystubs_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "gross_wage")
    private Long grossWage;

    @Column(name = "net_pay")
    private Long netPay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Long getId() {
        return id;
    }

    public Long getGrossWage() {
        return grossWage;
    }

    public void setGrossWage(Long grossWage) {
        this.grossWage = grossWage;
    }

    public Long getNetPay() {
        return netPay;
    }

    public void setNetPay(Long netPay) {
        this.netPay = netPay;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
