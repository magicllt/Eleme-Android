package com.example.zxq.elework.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderItemAndGoodsDO implements Serializable{
    int id;
    int orderId;
    GoodsDO goods;
    int num;
}
