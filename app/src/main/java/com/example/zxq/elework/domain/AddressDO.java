package com.example.zxq.elework.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 保存地址的pojo
 */
@Data
public class AddressDO implements Serializable{
    Integer id;
    Integer userId;
    String name;
    String gender;
    String phone;
    String address;
    String houseNum;
}
