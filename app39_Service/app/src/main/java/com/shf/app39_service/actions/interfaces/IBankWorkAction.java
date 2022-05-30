package com.shf.app39_service.actions.interfaces;

public interface IBankWorkAction extends INormalUserAction{
//    查询用户的信用
    void checkUserCredit();

//    冻结用户账户
    void freezeUserAccount();
}
