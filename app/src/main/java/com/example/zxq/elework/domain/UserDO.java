package com.example.zxq.elework.domain;

import java.io.Serializable;

import lombok.Data;


/**
 * 用户信息的pojo
 */
@Data
public class UserDO implements Serializable{
    private Integer id;
    private String phone;
    private String avatar;
    private String name;


}
