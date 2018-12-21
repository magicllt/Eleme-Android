package com.example.zxq.elework.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;

@Data
public class ShopDO implements Serializable{
    Integer id;
    String name;
    String img;
    BigDecimal grade;
    Integer monthSale;
    String address;
    String announcement;
    String category;
    String phone;
    String startTime;
    String closeTime;
}
