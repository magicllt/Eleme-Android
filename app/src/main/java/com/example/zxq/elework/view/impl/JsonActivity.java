package com.example.zxq.elework.view.impl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zxq.elework.R;
import com.example.zxq.elework.domain.OrderItemVO;
import com.example.zxq.elework.domain.OrderVo;
import com.example.zxq.elework.result.Result;
import com.example.zxq.elework.utils.HttpUtil;
import com.example.zxq.elework.utils.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.retrofitutils.listener.HttpResponseListener;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class JsonActivity extends AppCompatActivity {

    String json = "{\n" +
            "    \"id\": 0,\n" +
            "    \"shopId\": 1,\n" +
            "    \"userId\": 1,\n" +
            "    \"addressId\": 1,\n" +
            "    \"totalAmount\": 12,\n" +
            "    \"orderTime\": null,\n" +
            "    \"orderItems\": [\n" +
            "        {\n" +
            "            \"id\": 0,\n" +
            "            \"orderId\": 0,\n" +
            "            \"goodsId\": 1,\n" +
            "            \"num\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 0,\n" +
            "            \"orderId\": 0,\n" +
            "            \"goodsId\": 1,\n" +
            "            \"num\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 0,\n" +
            "            \"orderId\": 0,\n" +
            "            \"goodsId\": 1,\n" +
            "            \"num\": 1\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendJsonDataToServer();
            }
        });
    }

    private void sendJsonDataToServer(){
        OrderVo orderVo = new OrderVo();
        orderVo.setAddressId(1);
        orderVo.setShopId(1);
        orderVo.setTotalAmount(new BigDecimal(12.0));
        orderVo.setUserId(1);

        List<OrderItemVO> list = new ArrayList<OrderItemVO>();
        for (int i = 0; i < 3; ++i){
            OrderItemVO item = new OrderItemVO();
            item.setNum(1);
            item.setGoodsId(1);
            list.add(item);
        }
        orderVo.setOrderItems(list);
        HttpUtil.sendJsonRequest(UrlUtil.getOrderSave(), orderVo, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(JsonActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String responseData = response.body().string();
                    Gson gson = new Gson();
                    Result<Boolean> result = gson.fromJson(responseData, new TypeToken<Result<Boolean>>(){}.getType());
                    if (result.getCode() == 0 && result.getData() == true){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(JsonActivity.this, "success", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });
    }
}
