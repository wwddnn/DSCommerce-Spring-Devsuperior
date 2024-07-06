package com.devsuperior.dscommerce.entities;

// this is a ENUM
public enum OrderStatus {

    //starts with number zero.
    //names in enum, have capital letter.
    WAITING_PAYMENT,
    PAID,
    SHIPPED,
    DELIVERED,
    CANCELED;
}
