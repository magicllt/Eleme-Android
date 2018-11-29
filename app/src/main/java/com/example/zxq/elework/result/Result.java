package com.example.zxq.elework.result;

import lombok.Data;

/**
 * Created by LLT on 2018/11/29.
 */

@Data
public class Result<T> {
    int code;
    String msg;
    T data;
}
