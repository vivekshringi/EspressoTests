package com.mytaxi.android_demo;

import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

public class Login {
    Actions actions = new Actions();


    Matcher usernameField = ViewMatchers.withId(R.id.edt_username),
            passwordField = ViewMatchers.withId(R.id.edt_password),
            loginButn = ViewMatchers.withId(R.id.btn_login),
            searchText = ViewMatchers.withId(R.id.textSearch),
            toolBar = ViewMatchers.withId(R.id.toolbar),
            navUser = ViewMatchers.withId(R.id.nav_username),
            navBar = ViewMatchers.withContentDescription("Open navigation drawer"),
            logoutLink = ViewMatchers.withId((R.id.design_menu_item_text));

    public void loginUser(String userName, String passWord){
        if(checkLoginInfo()){
        actions.enterText(usernameField,userName);
        actions.enterText(passwordField,passWord);
        actions.clickOn(loginButn);
        actions.waitForElement(searchText);
        actions.clickOn(toolBar);
        actions.checkText(navUser,userName);
        }
    }

    public boolean checkLoginInfo(){
        return actions.isElementExist(loginButn);
    }

    public void logout(){
        if(!checkLoginInfo()){
            actions.clickOn(navBar);
            actions.waitForElement(logoutLink);
            actions.clickOn(logoutLink);
        }
    }
}
