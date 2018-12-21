package com.example.zxq.elework.domain;

import lombok.Data;

@Data
public class OrderItemAndGoodsDO {
    int id;
    int orderId;
    GoodsDO goods;
    int num;
}
