package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.DecimalFormat;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItem {
    private Integer id;
    private Integer num;
    private Double amount;
    private String bookName;
    private Double price;
    private Integer bookId;
    private Integer orderId;
    private String bookSrc;

}
