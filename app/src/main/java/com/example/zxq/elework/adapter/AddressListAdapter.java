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


    public interface OnViewHolderClickListener{
        void onLongClickView(int pos);
        void onClickView(int pos);
        void onClickEditImg(int pos);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.address_unit, parent, false);
        final ViewHolder holder = new ViewHolder(view);
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

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AddressDO addressDO = list.get(position);
        holder.setVal(addressDO);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<AddressDO>list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void removeListData(int pos){
        list.remove(pos);
        notifyItemRemoved(pos);
        notifyDataSetChanged();
        notifyItemRangeChanged(pos, list.size() - pos);
    }

    public void addListData(AddressDO addressDO){
        addListData(list.size(), addressDO);
    }

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

        public void setVal(AddressDO addressDO){
            addressText.setText(addressDO.getAddress());
            houseNumText.setText(addressDO.getHouseNum());
            nameText.setText(String.format("%s(%s)", addressDO.getName(), addressDO.getGender()));
            phoneText.setText(addressDO.getPhone());
        }
    }
}
