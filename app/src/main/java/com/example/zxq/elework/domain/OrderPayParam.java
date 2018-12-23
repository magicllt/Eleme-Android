package com.example.zxq.elework.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/***
 * 订单界面传入的pojo(包含Shop，Goods，总价)
 */
@Data
public class OrderPayParam implements Serializable{
    ShopDO shopDO;
    List<OrderItemAndGoodsDO> goods;
    BigDecimal totalAmount;
}
