package com.example.zxq.elework.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 保存商品信息的pojo
 */
@Data
public class GoodsDO {
    Integer id;
    Integer shopId;
    String name;
    String img;
    BigDecimal price;
    Integer sales;
}
