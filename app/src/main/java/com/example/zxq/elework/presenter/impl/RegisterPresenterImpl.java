package com.example.zxq.elework.presenter.impl;

import com.example.zxq.elework.domain.UserDO;
import com.example.zxq.elework.model.listener.AbstractOnModelFinishedListener;
import com.example.zxq.elework.model.RegisterModel;
import com.example.zxq.elework.model.impl.RegisterModelImpl;
import com.example.zxq.elework.presenter.RegisterPresenter;
import com.example.zxq.elework.utils.ValidatorUtil;
import com.example.zxq.elework.view.RegisterView;

/**
 * Created by LLT on 2018/12/16.
 * RegisterPresenter的实现类
 */
public class RegisterPresenterImpl implements RegisterPresenter {

    private final RegisterView registerView;
    private final RegisterModel registerModel;

    public RegisterPresenterImpl(RegisterView registerView){
        this.registerView = registerView;
        registerModel = new RegisterModelImpl();
    }

    /**
     * 注册
     * @param phone 手机号
     * @param pwd 密码
     * @param pwd2 确认密码
     */
    @Override
    public void register(String phone, String pwd, String pwd2) {
        /// 检验数据有效性，包括手机号是否有效，密码不能为空，密码和确认密码要求一致
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
            /// 检验通过了，调用model.register()方法进行注册
            registerModel.register(phone, pwd, new AbstractOnModelFinishedListener(registerView) {
                @Override
                public void success(Object obj) {
                    /// 注册成功，显示信息，提示调用view.registerSuccess()方法
                    registerView.showMsg("注册成功");
                    registerView.registerSuccess((UserDO)(obj));
                }
            });
        }
    }
}
