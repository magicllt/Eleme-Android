package com.example.zxq.elework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zxq.elework.R;
import com.example.zxq.elework.domain.GoodsDO;
import com.example.zxq.elework.utils.DataDealUtil;
import com.example.zxq.elework.utils.UrlUtil;

import java.util.List;

/**
 * Created by LLT on 2018/12/20.
 */

public class ShopGoodsListAdapter extends RecyclerView.Adapter<ShopGoodsListAdapter.GoodsHolder> {

    List<GoodsDO>list;

    List<Integer> goodsNumList;

    Context context;

    onGoodsNumChangeListener listener;

    public ShopGoodsListAdapter(List<GoodsDO> list, List<Integer> goodsNumList, Context context, onGoodsNumChangeListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
        this.goodsNumList = goodsNumList;
    }

    @Override
    public GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.shop_right_list_item, parent, false);
        final GoodsHolder holder = new GoodsHolder(view, context);
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++holder.goodsNum;
                holder.updateGoodsNumText();
                listener.onChange(holder.getAdapterPosition(), holder.goodsNum);
            }
        });
        holder.subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.goodsNum > 0){
                    --holder.goodsNum;
                    holder.updateGoodsNumText();
                    listener.onChange(holder.getAdapterPosition(), holder.goodsNum);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(GoodsHolder holder, int position) {
        GoodsDO goodsDO = list.get(position);
        holder.goodsNum = goodsNumList.get(position);
        holder.setData(goodsDO);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface onGoodsNumChangeListener{
        void onChange(int idx, int num);
    }

    static public class GoodsHolder extends RecyclerView.ViewHolder{

        GoodsDO data;
        private final View view;
        private final Context context;
        private final ImageView goodsImg;
        private int goodsNum;
        private final TextView nameText;
        private final TextView salesText;
        private final TextView priceText;
        private final TextView cntText;
        private final Button subBtn;
        private final Button addBtn;

        public GoodsHolder(View itemView, Context context) {
            super(itemView);
            this.view = itemView;
            this.context = context;
            //goodsNum = 0;
            goodsImg = (ImageView)view.findViewById(R.id.shop_goods_list_item_goods_img);
            nameText = (TextView)view.findViewById(R.id.shop_goods_list_item_goods_name_text);
            salesText = (TextView)view.findViewById(R.id.shop_goods_list_item_sales_text);
            priceText = (TextView)view.findViewById(R.id.shop_goods_list_item_price_text);
            cntText = (TextView)view.findViewById(R.id.shop_goods_list_item_num_text);
            subBtn = (Button)view.findViewById(R.id.shop_goods_list_item_sub_btn);
            addBtn = (Button)view.findViewById(R.id.shop_goods_list_item_add_btn);
        }

        void setData(GoodsDO data){
            this.data = data;
            Glide.with(context).load(UrlUtil.getImageUrl(data.getImg())).into(goodsImg);
            nameText.setText(data.getName());
            salesText.setText("月售" + data.getSales().toString());
            priceText.setText(DataDealUtil.convertToMoney(data.getPrice()));
            cntText.setText("" + goodsNum);
        }

        void updateGoodsNumText(){
            cntText.setText("" + goodsNum);
        }
    }
}
