package com.example.zxq.elework.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单列表数据的pojo
 */
@Data
public class OrderListItemBO {
    int id;
    Date orderTime;
    BigDecimal totalAmount;
    ShopOrderBO shop;
    String firstGoodsName;
    int goodsCnt;
}
