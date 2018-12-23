package com.example.zxq.elework.domain;

import lombok.Data;


/**
 * 订单项的pojo
 */
@Data
public class OrderItemVO {
    int id;
    int orderId;
    int goodsId;
    int num;
}
