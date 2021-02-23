package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

      private Integer id;
      private String name;
      private String detail;
      private String zipCode;
      private String phone;
      private Integer userId;

}
