package com.gmibank.MyTest;

import com.gmibank.pages.LoginPage;
import com.gmibank.pages.UserInfoPage;
import org.testng.annotations.Test;

public class MyTesClass extends LoginPage {

    UserInfoPage userInfoPage=new UserInfoPage();

    @Test
    public  void userInfo(){

        typeUserNameAndPassword("admin");





    }




}
