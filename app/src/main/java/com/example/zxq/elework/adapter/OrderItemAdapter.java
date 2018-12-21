package com.example.zxq.elework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zxq.elework.R;
import com.example.zxq.elework.domain.OrderItemAndGoodsDO;
import com.example.zxq.elework.utils.DataDealUtil;
import com.example.zxq.elework.utils.UrlUtil;

import java.util.List;

/**
 * Created by LLT on 2018/12/20.
 */

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemHolder> {

    List<OrderItemAndGoodsDO>list;

    Context context;

    public OrderItemAdapter(List<OrderItemAndGoodsDO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public OrderItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.order_retailed_goods_item, parent, false);
        OrderItemHolder holder = new OrderItemHolder(view, context);
        return holder;
    }

    @Override
    public void onBindViewHolder(OrderItemHolder holder, int position) {
        OrderItemAndGoodsDO data = list.get(position);
        holder.setData(data);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<OrderItemAndGoodsDO> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    static class OrderItemHolder extends RecyclerView.ViewHolder{

        private final View view;
        private final Context context;
        private final ImageView goodsImg;
        private final TextView goodsNameText;
        private final TextView goodsCntText;
        private final TextView goodsMoney;
        private OrderItemAndGoodsDO data;

        public OrderItemHolder(View itemView, Context context) {
            super(itemView);
            this.view = itemView;
            this.context = context;
            goodsImg = (ImageView)view.findViewById(R.id.order_goods_goods_img);
            goodsNameText = (TextView)view.findViewById(R.id.order_goods_goods_name_text);
            goodsCntText = (TextView)view.findViewById(R.id.order_goods_goods_cnt_text);
            goodsMoney = (TextView)view.findViewById(R.id.order_goods_money_text);
        }

        void setData(OrderItemAndGoodsDO data){
            this.data = data;
            Glide.with(context).load(UrlUtil.getImageUrl(data.getGoods().getImg())).into(goodsImg);
            goodsNameText.setText(data.getGoods().getName());
            goodsCntText.setText("x" + data.getNum());
            goodsMoney.setText(DataDealUtil.convertToMoney(data.getGoods().getPrice()));
        }
    }
}
