package com.example.zxq.elework.presenter.impl;

import com.example.zxq.elework.application.MyApplication;
import com.example.zxq.elework.domain.UserDO;
import com.example.zxq.elework.listener.AbstractOnModelFinishedListener;
import com.example.zxq.elework.listener.OnModelFinishedListener;
import com.example.zxq.elework.model.LoginModel;
import com.example.zxq.elework.model.impl.LoginModelImpl;
import com.example.zxq.elework.presenter.LoginPresenter;
import com.example.zxq.elework.utils.StringUtil;
import com.example.zxq.elework.utils.ValidatorUtil;
import com.example.zxq.elework.view.LoginView;

/**
 * Created by LLT on 2018/12/13.
 */

public class LoginPresenterImpl implements LoginPresenter {

    LoginView loginView;

    LoginModel loginModel;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView;
        loginModel = new LoginModelImpl();
    }

    @Override
    public void login(String phone, String pwd) {
        if (ValidatorUtil.isEmpty(phone) || ValidatorUtil.isMobile(phone) == false){
            loginView.showMsg("请输入正确的手机号码");
            loginView.clearEdit();
            return;
        }
        if (ValidatorUtil.isEmpty(pwd)){
            loginView.showMsg("密码不能为空");
            loginView.clearEdit();
            return;
        }
        loginModel.login(phone, pwd, new AbstractOnModelFinishedListener(loginView) {
            @Override
            public void success(Object obj) {
                MyApplication.setUser((UserDO)(obj));
                loginView.showMsg("登录成功");
                loginView.onloginSuccess();
            }
        });
    }
}

