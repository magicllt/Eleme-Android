package com.example.zxq.elework.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDetailDO {
    int id;
    ShopDO shop;
    int userId;
    AddressDO address;
    BigDecimal totalAmount;
    Date orderTime;
    List<OrderItemAndGoodsDO>orderItems;
}
