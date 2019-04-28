package com.mytaxi.android_demo;

import com.mytaxi.android_demo.activities.MainActivity;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;

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
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);

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