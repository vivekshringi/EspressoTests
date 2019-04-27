package com.mytaxi.android_demo;

import android.app.Activity;

import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class Driver {
    Actions actions = new Actions();

    Matcher myTaxiDemoField = ViewMatchers.withText("mytaxi demo"),
            textViewDriverName = ViewMatchers.withId(R.id.textViewDriverName),
            callButton = ViewMatchers.withId(R.id.fab),
            searchText = ViewMatchers.withId(R.id.textSearch);

    public void searchDriver(String enteredText, String driverName, Activity m){
        actions.waitForElement(myTaxiDemoField);
        actions.enterText(searchText,enteredText);
        actions.checkNameExistInSearchResult(driverName,m);
        actions.clickNameOnSearchResult(driverName,m);
        actions.waitForElement(textViewDriverName);
        actions.checkText(textViewDriverName,driverName);

    }

    public void callingDriver(){
        actions.waitForElement(callButton);
        actions.clickOn(callButton);
    }
}
