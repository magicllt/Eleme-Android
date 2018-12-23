package com.example.zxq.elework.model.listener;

/**
 * Created by LLT on 2018/12/13.
 * Model层的监听器，但model层完成任务之后，通过调用监听器通知presenter，任务已经完成
 */
public interface OnModelFinishedListener{
    /**
     * 任务完成时候调用
     * @param obj model执行成功处理完毕的数据
     */
    void success(Object obj);

    /**
     * 任务执行失败
     * @param msg 任务执行失败的信息
     */
    void error(String msg);

    /**
     * 没有网络
     */
    void noNet();
}
