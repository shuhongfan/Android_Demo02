// NormalUserAction.aidl
package com.shf.app39_service;

// Declare any non-default types here with import statements

interface NormalUserAction {
   //    存钱
       void saveMoney(float money);

   //    取钱
       double getMoney();

       //    贷款
       double loanMoney();
}