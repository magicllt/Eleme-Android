package com.example.zxq.elework.result;

import lombok.Data;

import java.util.List;

@Data
public class PageInfoBreif<T>{

    private int pageNum;
    private int pageSize;
    private int pages;
    List<T> list;
}
