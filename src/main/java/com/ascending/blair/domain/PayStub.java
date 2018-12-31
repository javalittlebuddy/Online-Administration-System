package com.ascending.blair.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "paystubs")
public class PayStub {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paystubs_id_seq")
    @SequenceGenerator(name = "paystubs_id_seq", sequenceName = "paystubs_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "gross_wage")
    private BigDecimal grossWage;

    @Column(name = "net_pay")
    private BigDecimal netPay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getGrossWage() {
        return grossWage;
    }

    public void setGrossWage(BigDecimal grossWage) {
        this.grossWage = grossWage;
    }

    public BigDecimal getNetPay() {
        return netPay;
    }

    public void setNetPay(BigDecimal netPay) {
        this.netPay = netPay;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
