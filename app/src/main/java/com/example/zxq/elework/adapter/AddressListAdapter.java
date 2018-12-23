package com.example.zxq.elework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zxq.elework.R;
import com.example.zxq.elework.domain.AddressDO;

import java.util.List;

/**
 * Created by LLT on 2018/12/21.
 * 地址列表适配器
 */

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.ViewHolder> {

    List<AddressDO>list;
    Context context;
    OnViewHolderClickListener listener;

    public AddressListAdapter(List<AddressDO> list, Context context, OnViewHolderClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    /**
     * 地址列表项的点击事件的监听者
     * view层传入具体的业务代码，通过传入不同的代码，可以实现地址组件
     */
    public interface OnViewHolderClickListener{
        /**
         * 长按事件
         * @param pos 响应的下标
         */
        void onLongClickView(int pos);

        /**
         * 点击事件
         * @param pos 响应的下标
         */
        void onClickView(int pos);

        /**
         * 点击编辑按钮的响应
         * @param pos 响应的下标
         */
        void onClickEditImg(int pos);
    }

    /**
     * 创建ViewHolder，完成事件绑定
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.address_unit, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        /// viewholder调用监听者的响应事件
        holder.addressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickView(holder.getAdapterPosition());
            }
        });
        holder.editImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickEditImg(holder.getAdapterPosition());
            }
        });
        holder.addressView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongClickView(holder.getAdapterPosition());
                return false;
            }
        });
        return holder;
    }

    /**
     * 完成ViewHolder的数据绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AddressDO addressDO = list.get(position);
        holder.setVal(addressDO);
    }

    /**
     * 返回列表项的数目
     * @return
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 给列表添加数据的函数
     * @param list
     */
    public void addAll(List<AddressDO>list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 移除列表项的函数
     * @param pos
     */
    public void removeListData(int pos){
        list.remove(pos);
        notifyItemRemoved(pos);
        notifyDataSetChanged();
        notifyItemRangeChanged(pos, list.size() - pos);
    }

    /**
     * 添加单个列表项
     * @param addressDO
     */
    public void addListData(AddressDO addressDO){
        addListData(list.size(), addressDO);
    }

    /**
     * 列表元素更新
     * @param addressDO
     * @param pos
     */
    public void updateListData(AddressDO addressDO, int pos){
        removeListData(pos);
        addListData(pos, addressDO);
    }


    private void addListData(int pos, AddressDO addressDO){
        list.add(pos, addressDO);
        notifyItemInserted(pos);
        notifyDataSetChanged();
        notifyItemRangeChanged(pos, list.size() - pos);
    }

    /**
     * ViewHolder的实现类
     */
    class ViewHolder extends RecyclerView.ViewHolder{

        View addressView;
        TextView addressText;
        TextView houseNumText;
        TextView nameText;
        TextView phoneText;
        ImageView editImg;

        public ViewHolder(View itemView) {
            super(itemView);
            addressView = itemView;
            addressText = (TextView)itemView.findViewById(R.id.address_unit_address_text);
            houseNumText = (TextView)itemView.findViewById(R.id.address_unit_house_num_text);
            nameText = (TextView)itemView.findViewById(R.id.address_unit_name_text);
            phoneText = (TextView)itemView.findViewById(R.id.address_unit_phone_text);
            editImg = (ImageView)itemView.findViewById(R.id.address_unit_edit_img);
        }


        /**
         * 完成ViewHolder的数据显示
         * @param addressDO 地址的数据分装类
         */
        public void setVal(AddressDO addressDO){
            addressText.setText(addressDO.getAddress());
            houseNumText.setText(addressDO.getHouseNum());
            nameText.setText(String.format("%s(%s)", addressDO.getName(), addressDO.getGender()));
            phoneText.setText(addressDO.getPhone());
        }
    }
}
