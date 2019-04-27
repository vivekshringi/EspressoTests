package com.mytaxi.android_demo;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mytaxi.android_demo.activities.MainActivity;

import android.content.Context;
import android.support.test.espresso.action.ViewActions;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.InstrumentationRegistry;
import androidx.test.rule.GrantPermissionRule;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
@SmallTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyTaxiEspressoTests {
    private static final String SEARCH_TEXT = "sa";
    private static final String SELECTED_DRIVER = "Sarah Scott";
    private MainActivity mActivity = null;
    Login loginActions = new Login();
    Driver driver = new Driver();
    GetCredentials getCredentials = new GetCredentials();

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setActivity() {
        mActivity = activityTestRule.getActivity();
    }

    @Test
    public void test001_Login(){
        String USERNAME = getCredentials.getValue("username");
        String PASSWORD = getCredentials.getValue("password");
        loginActions.loginUser(USERNAME,PASSWORD);
    }

    @Test
    public void test002_SearchAndCallingDriver(){
        if(loginActions.checkLoginInfo()){
            test001_Login();
        }
        driver.searchDriver(SEARCH_TEXT, SELECTED_DRIVER, mActivity);
        driver.callingDriver();
    }

   @Test
    public void test003_Logout() {
       loginActions.logout();
    }

}