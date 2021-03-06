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
import com.example.zxq.elework.domain.ShopListItemDO;
import com.example.zxq.elework.utils.UrlUtil;

import java.util.List;

/**
 * Created by LLT on 2018/12/17.
 * 店铺列表的适配器
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopHolder>{

    private final List<ShopListItemDO> list;
    private final Context context;
    private final ShopAdapterListener listener;

    public interface ShopAdapterListener{
        void onShopListItemClick(int id);
    }

    public ShopAdapter(List<ShopListItemDO> list, Context context, ShopAdapterListener listener){
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ShopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.shop_roughly, parent, false);
        final ShopHolder shopHolder = new ShopHolder(view, context);
        shopHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onShopListItemClick(shopHolder.data.getId());
            }
        });
        return shopHolder;
    }

    /**
     * 完成列表中该数据和ViewHolder的绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ShopHolder holder, int position) {
        ShopListItemDO item = list.get(position);
        holder.setData(item);
    }

    /**
     * 返回列表的元素个数
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
    public void addData(List<ShopListItemDO> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * ViewHolder的类
     */
    static class ShopHolder extends RecyclerView.ViewHolder{

        ShopListItemDO data;
        private View view;
        private final ImageView shopImg;
        private final TextView nameText;
        private final TextView scoreText;
        private final TextView salesText;
        private final Context context;

        public ShopHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            this.view = itemView;
            shopImg = (ImageView)view.findViewById(R.id.shop_roughly_img);
            nameText = (TextView)view.findViewById(R.id.shop_roughly_name);
            scoreText = (TextView)view.findViewById(R.id.shop_roughly_score);
            salesText = (TextView)view.findViewById(R.id.shop_roughly_sales);
        }

        /**
         * 完成ViewHolder的数据绑定，数据显示到对应的组件上
         * @param data
         */
        public void setData(ShopListItemDO data) {
            this.data = data;
            Glide.with(context).load(UrlUtil.getImageUrl(data.getImg())).into(shopImg);
            nameText.setText(data.getName());
            scoreText.setText(data.getGrade().toString());
            salesText.setText(data.getMonthSale().toString());
        }
    }
}
