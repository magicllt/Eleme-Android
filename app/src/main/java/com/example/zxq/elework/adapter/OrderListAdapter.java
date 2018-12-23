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
import com.example.zxq.elework.domain.OrderListItemBO;
import com.example.zxq.elework.utils.DataDealUtil;
import com.example.zxq.elework.utils.UrlUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by LLT on 2018/12/20.
 * 订单列表的适配器
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderItemHolder> {

    List<OrderListItemBO>list;

    Context context;

    OrderListListener listener;

    public OrderListAdapter(List<OrderListItemBO>list, Context context, OrderListListener listener){
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public OrderItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.order_item, parent, false);
        final OrderItemHolder holder = new OrderItemHolder(view, context);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOrderItemClick(holder.data.getId());
            }
        });
        return holder;
    }

    /**
     * 完成ViewHolder和列表中某个数据的绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(OrderItemHolder holder, int position) {
        OrderListItemBO item = list.get(position);
        holder.setData(item);
    }

    /**
     * 返回列表的元素的个数
     * @return
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 给列表添加元素
     * @param list
     */
    public void addData(List<OrderListItemBO> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 列表项点击事件的监听者
     */
    public interface OrderListListener{
        void onOrderItemClick(int id);
    }

    /**
     * ViewHolder的代码
     */
    static class OrderItemHolder extends RecyclerView.ViewHolder{

        OrderListItemBO data;
        Context context;
        private final View view;
        private final ImageView shopImg;
        private final TextView shopNameText;
        private final TextView orderTimeText;
        private final TextView goodsText;
        private final TextView moneyText;

        public OrderItemHolder(View itemView, Context context) {
            super(itemView);
            this.view = itemView;
            this.context = context;
            shopImg = (ImageView)view.findViewById(R.id.order_item_shop_img);
            shopNameText = (TextView)view.findViewById(R.id.order_item_shop_name_text);
            orderTimeText = (TextView)view.findViewById(R.id.order_item_order_time_text);
            goodsText = (TextView)view.findViewById(R.id.order_item_goods_text);
            moneyText = (TextView)view.findViewById(R.id.order_item_money_text);
        }

        /**
         * 完成ViewHolder和数据的绑定
         * @param data
         */
        public void setData(OrderListItemBO data){
            ;this.data = data;
            Glide.with(context).load(UrlUtil.getImageUrl(data.getShop().getShopImg())).into(shopImg);
            shopNameText.setText(data.getShop().getShopName());
            orderTimeText.setText(DataDealUtil.formatDate(data.getOrderTime()));
            goodsText.setText(convertToGoodsString(data));
            moneyText.setText(DataDealUtil.convertToMoney(data.getTotalAmount()));
        }

        private String convertToGoodsString(OrderListItemBO data) {
            String val = data.getFirstGoodsName();
            if (data.getGoodsCnt() > 1){
                val += String.format(" 等%d件商品", data.getGoodsCnt());
            }
            return val;
        }
    }

}
