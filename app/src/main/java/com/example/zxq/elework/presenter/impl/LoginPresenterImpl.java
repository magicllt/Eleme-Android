package com.example.zxq.elework.presenter.impl;

import com.example.zxq.elework.application.MyApplication;
import com.example.zxq.elework.domain.UserDO;
import com.example.zxq.elework.model.listener.AbstractOnModelFinishedListener;
import com.example.zxq.elework.model.LoginModel;
import com.example.zxq.elework.model.impl.LoginModelImpl;
import com.example.zxq.elework.presenter.LoginPresenter;
import com.example.zxq.elework.utils.ValidatorUtil;
import com.example.zxq.elework.view.LoginView;

/**
 * Created by LLT on 2018/12/13.
 * LoginPresenter的实现类
 */
public class LoginPresenterImpl implements LoginPresenter {

    LoginView loginView;

    LoginModel loginModel;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView;
        loginModel = new LoginModelImpl();
    }

    /**
     * 登录
     * @param phone 手机号
     * @param pwd 密码
     */
    @Override
    public void login(String phone, String pwd) {
        /// 检验手机号是否符合正则，密码是否为空
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
        /// model处理登录请求
        loginModel.login(phone, pwd, new AbstractOnModelFinishedListener(loginView) {
            @Override
            public void success(Object obj) {
                /// 登录成功，application保存user信息，打印登录成功的信息，调用view的onSuccess()方法
                MyApplication.setUser((UserDO)(obj));
                loginView.showMsg("登录成功");
                loginView.onloginSuccess();
            }
        });
    }
}

