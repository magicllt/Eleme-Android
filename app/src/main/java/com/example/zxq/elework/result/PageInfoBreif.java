package com.example.zxq.elework.result;

import lombok.Data;

import java.util.List;

/**
 * 分页信息的封装类
 */
@Data
public class PageInfoBreif<T>{

    /// 当前页
    private int pageNum;
    /// 页大小
    private int pageSize;
    /// 总页数
    private int pages;
    /// 当前页对应的数据
    List<T> list;
}
