package com.example.estore.paymentsservice.core;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "payments")
@Entity
public class PaymentEntity implements Serializable {
    private static final long serialVersionUID = 5313493413859894403L;

    @Id
    private String paymentId;
    @Column
    public String orderId;
}
