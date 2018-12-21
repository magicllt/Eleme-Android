package com.example.zxq.elework.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by LLT on 2018/12/21.
 */

@Data
public class OrderPayParam implements Serializable{
    ShopDO shopDO;
    List<OrderItemAndGoodsDO> goods;
    BigDecimal totalAmount;
}
