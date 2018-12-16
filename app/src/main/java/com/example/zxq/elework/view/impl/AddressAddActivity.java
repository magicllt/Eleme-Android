package com.example.zxq.elework.view.impl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zxq.elework.R;
import com.example.zxq.elework.domain.AddressDO;
import com.example.zxq.elework.presenter.AddressAddPresenter;
import com.example.zxq.elework.presenter.impl.AddressAddPresenterImpl;
import com.example.zxq.elework.view.AddressAddView;
import com.example.zxq.elework.view.base.BaseActivity;

public class AddressAddActivity extends BaseActivity implements AddressAddView{

    public static final int TYPE_ADD = 0;

    public static final int TYPE_UPDATE = 1;

    private int SELECT_COLOR;

    private int UNSELECT_COLOR;

    static final String TYPE_TAG = "type";

    static final String ADDRESS_TAG = "address";

    static final String POS_TAG = "pos";

    private int currentType;

    private int pos = -1;

    private AddressDO myAddress;

    private TextView titleText;
    private EditText nameEdit;
    private Button maleBtn;
    private Button femaleBtn;
    private EditText phoneEdit;
    private EditText addressEdit;
    private EditText houseEdit;
    private Button saveBtn;
    private AddressAddPresenter addressAddPresenter;

    static public void addActionStart(AppCompatActivity context){
        Intent intent = new Intent(context, AddressAddActivity.class);
        intent.putExtra(TYPE_TAG, TYPE_ADD);
        context.startActivityForResult(intent, TYPE_ADD);
    }

    static public void updateActionStart(AppCompatActivity context, AddressDO addressDO, int pos){
        Intent intent = new Intent(context, AddressAddActivity.class);
        intent.putExtra(TYPE_TAG, TYPE_UPDATE);
        intent.putExtra(ADDRESS_TAG, addressDO);
        intent.putExtra(POS_TAG, pos);
        context.startActivityForResult(intent, TYPE_UPDATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_add);
        initWidget();
        judegCurrentType();
        addressAddPresenter = new AddressAddPresenterImpl(this);
    }

    private void judegCurrentType() {
        Intent intent = getIntent();
        currentType = intent.getIntExtra(TYPE_TAG, TYPE_ADD);
        if (currentType == TYPE_UPDATE){
            pos = intent.getIntExtra(POS_TAG, 0);
            myAddress = (AddressDO) intent.getSerializableExtra(ADDRESS_TAG);
            showAddressInfo(myAddress);
        }
    }

    private void initWidget() {
        titleText = (TextView)findViewById(R.id.address_add_title_text);
        nameEdit = (EditText)findViewById(R.id.address_add_name_edit);
        maleBtn = (Button)findViewById(R.id.address_add_male_btn);
        femaleBtn = (Button)findViewById(R.id.address_add_female_btn);
        phoneEdit = (EditText)findViewById(R.id.address_add_phone_edit);
        addressEdit = (EditText)findViewById(R.id.address_add_address_edit);
        houseEdit = (EditText)findViewById(R.id.address_add_house_edit);
        saveBtn = (Button)findViewById(R.id.address_add_save_btn);

        femaleBtn.setOnClickListener(onClickListener);
        maleBtn.setOnClickListener(onClickListener);
        saveBtn.setOnClickListener(onClickListener);

        SELECT_COLOR = getResources().getColor(R.color.dodgerblue);
        UNSELECT_COLOR = getResources().getColor(R.color.dimgray);
    }

    @Override
    public void showAddressInfo(AddressDO addressDO) {
        titleText.setText("修改地址");
        nameEdit.setText(addressDO.getName());
        if ("先生".equals(addressDO.getGender())){
            clickMaleBtn();
        }else if ("女士".equals(addressDO.getGender())){
            clickFemaleBtn();
        }
        phoneEdit.setText(addressDO.getPhone());
        addressEdit.setText(addressDO.getAddress());
        houseEdit.setText(addressDO.getHouseNum());
    }

    @Override
    public void clickMaleBtn() {
        femaleBtn.setTextColor(UNSELECT_COLOR);
        if (maleBtn.getCurrentTextColor() == SELECT_COLOR){
            maleBtn.setTextColor(UNSELECT_COLOR);
        }else{
            maleBtn.setTextColor(SELECT_COLOR);
        }
    }

    @Override
    public void clickFemaleBtn() {
        maleBtn.setTextColor(UNSELECT_COLOR);
        if (femaleBtn.getCurrentTextColor() == SELECT_COLOR){
            femaleBtn.setTextColor(UNSELECT_COLOR);
        }else{
            femaleBtn.setTextColor(SELECT_COLOR);
        }
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(AddressAddActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == maleBtn){
                clickMaleBtn();
                return;
            }else if (view == femaleBtn){
                clickFemaleBtn();
                return;
            }else if (view == saveBtn){
                updateAddressInfo();
                if (currentType == TYPE_ADD){
                    addressAddPresenter.saveAddress(myAddress);
                }else{
                    addressAddPresenter.updateAddress(myAddress);
                }
            }
        }
    };

    private void updateAddressInfo(){
        if (myAddress == null){
            myAddress = new AddressDO();
        }
        myAddress.setName(nameEdit.getText().toString());
        myAddress.setPhone(phoneEdit.getText().toString());
        myAddress.setAddress(addressEdit.getText().toString());
        myAddress.setHouseNum(houseEdit.getText().toString());
        myAddress.setGender(getGender());
    }

    private String getGender() {
        if (maleBtn.getCurrentTextColor() == SELECT_COLOR){
            return "先生";
        }
        if (femaleBtn.getCurrentTextColor() == SELECT_COLOR){
            return "女士";
        }
        return "";
    }


    @Override
    public void updateAddressFinish(AddressDO addressDO) {
        Intent intent = new Intent();
        intent.putExtra(ADDRESS_TAG, addressDO);
        intent.putExtra(POS_TAG, pos);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void addAddressFinish(AddressDO addressDO) {
        Intent intent = new Intent();
        intent.putExtra(ADDRESS_TAG, addressDO);
        setResult(RESULT_OK, intent);
        finish();
    }
}
