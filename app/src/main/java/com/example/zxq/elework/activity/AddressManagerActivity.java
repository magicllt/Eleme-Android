//package com.example.zxq.elework.activity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.View;
//
//
//import com.example.zxq.elework.R;
//import com.example.zxq.elework.activity.baseActivity.StateActivity2;
//import com.example.zxq.elework.activity.view.layout.StateLayout;
//import com.example.zxq.elework.activity.view.recyclerView.AddressAdapter;
//import com.example.zxq.elework.domain.AddressDO;
//import com.example.zxq.elework.result.Result;
//import com.example.zxq.elework.utils.HttpUtil;
//import com.example.zxq.elework.utils.UrlUtil;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ListIterator;
//
//import okhttp3.Call;
//import okhttp3.Response;
//
//public class AddressManagerActivity extends StateActivity2 {
//
//    //保存用户的所有的收货地址
//    private List<AddressDO> addressDOList = new ArrayList<AddressDO>();
//
//    AddressAdapter addressAdapter;
//
//    RecyclerView recyclerView;
//
//    /**
//     * 其他活动跳转到本活动调用的方法
//     * @param context 调用本方法的活动的上下文
//     */
//    static public void actionStart(Context context){
//        Intent intent = new Intent(context, AddressManagerActivity.class);
//        context.startActivity(intent);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    /**
//     * 当界面可以显示的时候调用
//     */
//    @Override
//    protected void onStart() {
//        super.onStart();
//        getSupportActionBar().hide();
//        //设置列表的相关参数
//        recyclerView = (RecyclerView)findViewById(R.id.activity_address_manager_recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//    }
//
//    /**
//     * 设置请求成功的时候调用的界面
//     * @return
//     */
//    @Override
//    public View getsuccessView() {
//        return View.inflate(this, R.layout.activity_address_manager, null);
//    }
//
//    /**
//     * 设置加载器
//     * @return 加载器的实例
//     */
//    @Override
//    public StateLayout.OnReloadListener bindOnReloadListener() {
//        return new StateLayout.OnReloadListener() {
//            @Override
//            public void onReload() {
//                getAddressFromServer();
//            }
//        };
//    }
//
//    /**
//     * 从服务器获取数据
//     */
//    private void getAddressFromServer(){
//        /**
//         * 访问获取地址列表的服务器接口
//         */
//        HttpUtil.sendOkHttpGetRequest(UrlUtil.getAddressList(), new okhttp3.Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                //访问失败，调用失败界面
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        stateLayout.showErrorView();
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                //判断请求码是不是200
//                if (response.isSuccessful()){
//                    //解析数据
//                    Gson gson = new Gson();
//                    String responseData = response.body().string();
//                    Log.d(TAG, "onResponse: " + responseData);
//                    final Result<List<AddressDO>> result = gson.fromJson(responseData, new TypeToken<Result<List<AddressDO>>>(){}.getType());
//                    if (result.getCode() == 0){
//                        //显示成功界面，把数据放到适配器里面
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                stateLayout.showSuccessView();
//                                addressAdapter = new AddressAdapter(result.getData());
//                                recyclerView.setAdapter(addressAdapter);
//                            }
//                        });
//                    }else{
//                        //数据访问失败 调用失败界面
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                HttpUtil.ResultFail(result);
//                                stateLayout.showErrorView();
//                            }
//                        });
//                    }
//                }else{
//                    //访问请求码非200 调用失败界面
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            HttpUtil.accessServerFail();
//                            stateLayout.showErrorView();
//                        }
//                    });
//                }
//            }
//        });
//    }
//
//}
