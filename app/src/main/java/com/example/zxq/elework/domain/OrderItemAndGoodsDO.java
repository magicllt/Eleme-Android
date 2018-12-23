package com.example.zxq.elework.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 订单项(包含Goods)的pojo
 */
@Data
public class OrderItemAndGoodsDO implements Serializable{
    int id;
    int orderId;
    GoodsDO goods;
    int num;
}
