package com.example.zxq.elework.activity.view.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zxq.elework.R;
import com.example.zxq.elework.domain.AddressDO;

import java.util.List;

/**
 * Created by LLT on 2018/12/1.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder>{

    private List<AddressDO> addressDOList;

    public AddressAdapter(List<AddressDO> addressDOList) {
        this.addressDOList = addressDOList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.address_unit, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.addressView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(view.getContext(), "Long Click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        holder.editImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Click", Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AddressDO addressDO = addressDOList.get(position);
        holder.setVal(addressDO);
    }

    @Override
    public int getItemCount() {
        return addressDOList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

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
