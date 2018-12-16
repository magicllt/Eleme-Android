package com.example.zxq.elework.presenter.impl;

import com.example.zxq.elework.domain.UserDO;
import com.example.zxq.elework.listener.AbstractOnModelFinishedListener;
import com.example.zxq.elework.model.RegisterModel;
import com.example.zxq.elework.model.impl.RegisterModelImpl;
import com.example.zxq.elework.presenter.RegisterPresenter;
import com.example.zxq.elework.utils.ValidatorUtil;
import com.example.zxq.elework.view.RegisterView;

/**
 * Created by LLT on 2018/12/16.
 */

public class RegisterPresenterImpl implements RegisterPresenter {

    private final RegisterView registerView;
    private final RegisterModel registerModel;

    public RegisterPresenterImpl(RegisterView registerView){
        this.registerView = registerView;
        registerModel = new RegisterModelImpl();
    }

    @Override
    public void register(String phone, String pwd, String pwd2) {
        if (ValidatorUtil.isEmpty(phone) || ValidatorUtil.isEmpty(pwd) || ValidatorUtil.isEmpty(pwd2)){
            registerView.showMsg("输入内容不能为空");
            return;
        }else if (ValidatorUtil.isMobile(phone) == false){
            registerView.showMsg("请输入正确的手机号码");
            return;
        }else if (pwd.equals(pwd2) == false){
            registerView.showMsg("输入密码不一致");
            return;
        }else{
            registerModel.register(phone, pwd, new AbstractOnModelFinishedListener(registerView) {
                @Override
                public void success(Object obj) {
                    registerView.showMsg("注册成功");
                    registerView.registerSuccess((UserDO)(obj));
                }
            });
        }
    }
}
