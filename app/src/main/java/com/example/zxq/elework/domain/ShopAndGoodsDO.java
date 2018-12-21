package com.example.zxq.elework.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.List;

/**
 * 数据表shop的映射类
 */
@Data
public class ShopAndGoodsDO {
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
    List<GoodsDO> goods;
}
