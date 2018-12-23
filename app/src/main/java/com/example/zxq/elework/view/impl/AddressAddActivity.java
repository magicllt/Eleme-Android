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

/**
 * AddressAddView的实现类
 */
public class AddressAddActivity extends BaseActivity implements AddressAddView{

    /// 添加地址类型
    public static final int TYPE_ADD = 0;

    /// 更新地址类型
    public static final int TYPE_UPDATE = 1;

    /// 先生女士按钮的选中颜色
    private int SELECT_COLOR;

    /// 先生女士未选中颜色
    private int UNSELECT_COLOR;

    /// 在intent中保存type的类型Tag
    static final String TYPE_TAG = "type";

    /// 在intent中保存address的tag
    static final String ADDRESS_TAG = "address";

    /// 在intent中保存pos的tag
    static final String POS_TAG = "pos";

    /// 当前类型 TYPE_ADD 或者 TYPE_UPDATE
    private int currentType;

    /// 当前操作的元素的下标
    private int pos = -1;

    /// 维护的地址
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

    /**
     * 添加地址的启动接口
     * @param context 上下文
     */
    static public void addActionStart(AppCompatActivity context){
        /// 构建intent，设置type为添加
        Intent intent = new Intent(context, AddressAddActivity.class);
        intent.putExtra(TYPE_TAG, TYPE_ADD);
        context.startActivityForResult(intent, TYPE_ADD);
    }

    /**
     * 更新地址的启动接口
     * @param context 上下文
     * @param addressDO 地址
     * @param pos 该地址在原先适配器中的位置
     */
    static public void updateActionStart(AppCompatActivity context, AddressDO addressDO, int pos){
        /// 构建intent，传入type，address，pos
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
        /// 判断当前type
        judegCurrentType();
        addressAddPresenter = new AddressAddPresenterImpl(this);
    }

    /**
     * 判断当前的type，并且从intent里面取出所有的数据
     */
    private void judegCurrentType() {
        Intent intent = getIntent();
        currentType = intent.getIntExtra(TYPE_TAG, TYPE_ADD);
        /// 如果当前类型是更新类型, 取出pos, address, 同时渲染数据
        if (currentType == TYPE_UPDATE){
            pos = intent.getIntExtra(POS_TAG, 0);
            myAddress = (AddressDO) intent.getSerializableExtra(ADDRESS_TAG);
            showAddressInfo(myAddress);
        }
    }

    /**
     * 初始化所有的组件，设置监听事件
     */
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

    /**
     * 展示地址的信息
     * @param addressDO 地址信息封装类
     */
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

    /**
     * 点击先生对组件的ui更新代码
     */
    @Override
    public void clickMaleBtn() {
        femaleBtn.setTextColor(UNSELECT_COLOR);
        if (maleBtn.getCurrentTextColor() == SELECT_COLOR){
            maleBtn.setTextColor(UNSELECT_COLOR);
        }else{
            maleBtn.setTextColor(SELECT_COLOR);
        }
    }

    /**
     * 点击女士对ui的更新代码
     */
    @Override
    public void clickFemaleBtn() {
        maleBtn.setTextColor(UNSELECT_COLOR);
        if (femaleBtn.getCurrentTextColor() == SELECT_COLOR){
            femaleBtn.setTextColor(UNSELECT_COLOR);
        }else{
            femaleBtn.setTextColor(SELECT_COLOR);
        }
    }

    /**
     * 展示消息
     * @param msg 消息
     */
    @Override
    public void showMsg(String msg) {
        Toast.makeText(AddressAddActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 所有点击事件的OnClickListener
     * 先生：按钮状态切换到先生
     * 女士：按钮状态切换到女士
     * 保存按钮：根据当前type调用presenter进行相应操作
     */
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

    /**
     * 将组件上的地址信息转化到addrees中
     */
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

    /**
     * 判断性别
     * @return
     */
    private String getGender() {
        if (maleBtn.getCurrentTextColor() == SELECT_COLOR){
            return "先生";
        }
        if (femaleBtn.getCurrentTextColor() == SELECT_COLOR){
            return "女士";
        }
        return "";
    }


    /**
     * 地址更新成功调用
     * 返回数据给上一层
     * @param addressDO 地址的信息
     */
    @Override
    public void updateAddressFinish(AddressDO addressDO) {
        Intent intent = new Intent();
        intent.putExtra(ADDRESS_TAG, addressDO);
        intent.putExtra(POS_TAG, pos);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * 添加成功调用
     * 返回数据给上一层
     * @param addressDO 地址的信息
     */
    @Override
    public void addAddressFinish(AddressDO addressDO) {
        Intent intent = new Intent();
        intent.putExtra(ADDRESS_TAG, addressDO);
        setResult(RESULT_OK, intent);
        finish();
    }
}
