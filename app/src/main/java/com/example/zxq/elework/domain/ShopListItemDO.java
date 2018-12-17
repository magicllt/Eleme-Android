package com.example.zxq.elework.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;

/**
 * 店铺简略信息的数据映射类
 */
@Data
public class ShopListItemDO {
    Integer id;
    String name;
    String img;
    BigDecimal grade;
    Integer monthSale;
}
