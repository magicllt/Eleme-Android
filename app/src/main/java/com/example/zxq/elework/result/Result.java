package com.example.zxq.elework.result;

import lombok.Data;

/**
 * Created by LLT on 2018/11/29.
 * 服务器响应数据形式为Result(code, msg, data)
 * 本类为result的泛型类
 */
@Data
public class Result<T> {
    /// 结果响应代码，0代表响应成功
    int code;
    /// 响应附加的信息，当code不为0时候，为错误的信息
    String msg;
    /// 泛型数据
    T data;
}
