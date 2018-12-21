package com.example.zxq.elework.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderVo {
    int id;
    int shopId;
    int userId;
    int addressId;
    BigDecimal totalAmount;
    Date orderTime;
    List<OrderItemVO> orderItems;
}
