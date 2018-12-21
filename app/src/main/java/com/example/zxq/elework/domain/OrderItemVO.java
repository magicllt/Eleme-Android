package com.example.zxq.elework.domain;

import lombok.Data;


@Data
public class OrderItemVO {
    int id;
    int orderId;
    int goodsId;
    int num;
}
