//package com.example.zxq.elework.activity.baseActivity;
//
//import android.os.Bundle;
//import android.view.View;
//
//import com.example.zxq.elework.activity.view.layout.StateLayout;
//
///**
// * 包含网路请求状态布局的一个抽象活动
// */
//public abstract class StateActivity2 extends BaseActivity2 {
//
//    protected StateLayout stateLayout;
//
//    protected StateLayout.OnReloadListener listener;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        initView();//初始化界面
//        //发起网络请求
//        stateLayout.pullRequest();
//    }
//
//    /**
//     * 初始化组件
//     */
//    private void initView() {
//        //设置一个新的状态布局
//        stateLayout = new StateLayout(this);
//        //绑定成功界面和监听器
//        stateLayout.bindSuccessView(getsuccessView());
//        listener = bindOnReloadListener();
//        stateLayout.setOnReloadListener(listener);
//        //绑定布局
//        setContentView(stateLayout);
//    }
//
//    /**
//     * 设置正常显示的界面
//     * @return
//     */
//    abstract public View getsuccessView();
//
//    /**
//     * 设置数据加载的监听者
//     * 失败要调用stateLayout.showErrorView()
//     * 成功要调用stateLayout.showSuccessView();
//     * @return
//     */
//    abstract public StateLayout.OnReloadListener bindOnReloadListener();
//}
