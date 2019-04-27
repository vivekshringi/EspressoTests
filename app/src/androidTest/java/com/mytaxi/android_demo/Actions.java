package com.mytaxi.android_demo;
import android.app.Activity;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.mytaxi.android_demo.activities.MainActivity;

import junit.framework.Assert;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class Actions {

    private static final int TIME_OUT_SEC = 5;

    public void enterText(Matcher elem , String text){
        onView(elem).check(matches(isDisplayed())).perform(typeText(text));
        onView(elem).check(ViewAssertions.matches(ViewMatchers.withText(text)));
    }

    public void clickOn(Matcher elem){
        onView(elem).check(matches(isDisplayed())).perform(click());
    }

    public void waitForElement(Matcher elem){
             for(int i=0;i<=TIME_OUT_SEC;i++){
                 try{
                     Thread.sleep(1000);
                     if(isElementExist(elem)){
                        return;
                     }
                 }
                 catch(Exception e){
                     e.printStackTrace();
                 }
             }
             Assert.fail("Element is not found After Waiting");
    }

    public boolean isElementExist(Matcher elem){
        try{
        onView(elem).check(matches(isDisplayed()));
        return true;
        }
        catch(Exception e){
            return false;

        }
    }

    public void checkText(Matcher elem, String expectedText){
        try{
            onView(elem).check(matches(withText(expectedText)));
        }
        catch(Exception e){
            Assert.fail("Failed");
        }
    }

    public void checkNameExistInSearchResult(String expectedDriver, Activity M){
        onView(withText(expectedDriver)).inRoot(withDecorView(not(is(M.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    public void clickNameOnSearchResult(String expectedDriver, Activity M){
        onView(withText(expectedDriver)).inRoot(withDecorView(not(is(M.getWindow().getDecorView())))).perform(click());
    }
}
