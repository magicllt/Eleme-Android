package com.example.zxq.elework.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用于提交订单信息的pojo
 */
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
