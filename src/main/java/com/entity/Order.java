package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {

    private Integer id;
    private String orderNumber;
    private Date orderTimes;
    private String addrUser;
    private String addrName;
    private double totalPrice;
    private Integer userId;
    private Integer addrId;
    private Integer isPay;

}
