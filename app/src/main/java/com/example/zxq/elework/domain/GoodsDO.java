package com.example.zxq.elework.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsDO {
    Integer id;
    Integer shopId;
    String name;
    String img;
    BigDecimal price;
    Integer sales;
}
