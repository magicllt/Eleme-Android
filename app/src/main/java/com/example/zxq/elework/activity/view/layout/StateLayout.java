package com.example.zxq.elework.activity.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.zxq.elework.R;

/**
 * Created by LLT on 2018/12/1.
 */

/**
 * 本类为包含网络请求成功，失败，加载三个状态的一个布局
 */
public class StateLayout extends FrameLayout {
    //加载的布局
    private View loadingView;
    //失败的布局
    private View errorView;
    //成功的布局
    private View successView;

    public StateLayout(Context context) {
        this(context,null);
    }
    public StateLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public StateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();//初始化View
    }
    /**
     * 添加那3个子View：加载中的，加载成功的，加载失败的
     */
    private void initView() {
        //1.加载loadingView
        loadingView = View.inflate(getContext(), R.layout.progressbar_layout, null);
        addView(loadingView);
        //2.添加失败的View
        errorView = View.inflate(getContext(), R.layout.network404_layout, null);
        Button btn_reload = (Button) errorView.findViewById(R.id.network404_layout_reload);
        btn_reload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pullRequest();
            }
        });
        addView(errorView);
        //3.加载成功的View在各界面是不同的，所以提供一个方法bindsucessview动态添加
        //一开始隐藏所有的View
        hideAll();
    }

    /**
     * 发起网络请求
     */
    public void pullRequest(){
        //加载的界面
        showLoadingView();
        //调用监听器的实际请求网络的方法
        listener.onReload();
    }
    /**
     * 添加一个成功的View进来
     */
    public void bindSuccessView(View view){
        successView = view;
        if(successView!=null){
            successView.setVisibility(View.INVISIBLE);//隐藏successView
            //将它添加进来
            addView(successView);
        }
    }
    public void showSuccessView(){
        //先隐藏其他的
        hideAll();
        if(successView!=null){
            successView.setVisibility(View.VISIBLE);
        }
    }
    public void showErrorView(){
        //先隐藏其他的
        hideAll();
        errorView.setVisibility(View.VISIBLE);
    }
    public void showLoadingView(){
        //先隐藏其他的
        hideAll();
        loadingView.setVisibility(View.VISIBLE);
    }
    /**
     * 隐藏所有的View
     */
    public void hideAll(){
        //设置各界面不可见，同时让他们不重新layout，要用的时候直接show就行了
        loadingView.setVisibility(View.INVISIBLE);
        errorView.setVisibility(View.INVISIBLE);
        if(successView!=null){
            successView.setVisibility(View.INVISIBLE);
        }
    }

    //发起网络请求调用的监听器
    private OnReloadListener listener;
    public void setOnReloadListener(OnReloadListener listener){
        this.listener = listener;
    }
    public interface OnReloadListener{
        /**
         * 当重新加载的按钮被点击的时候调用
         */
        void onReload();
    }
}
