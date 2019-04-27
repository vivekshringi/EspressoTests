package com.mytaxi.android_demo;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mytaxi.android_demo.activities.MainActivity;
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
    private String seed = "a1f30d446f820665";
    private static final String SEARCH_TEXT = "sa";
    private static final String SELECTED_DRIVER = "Sarah Scott";
    private MainActivity mActivity = null;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setActivity() {
        mActivity = activityTestRule.getActivity();
    }

    @Test
    public void test001_Login() throws Exception {
        String USERNAME = fetchingLoginInfo(seed,"username");
        String PASSWORD = fetchingLoginInfo(seed,"password");
        onView(withId(R.id.edt_username))
                .perform(ViewActions.typeText(USERNAME),closeSoftKeyboard());
        onView(withId(R.id.edt_password))
                .perform(typeText(PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.btn_login))
                .perform(click());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.textSearch)).check(matches(isClickable()));
        onView(withId(R.id.toolbar))
                .perform(click());
        onView(withId(R.id.nav_username)).check(matches(withText(USERNAME)));
    }

    @Test
    public void test002_SearchDriver(){
        onView(withId(R.id.textSearch)).perform(click(),typeText(SEARCH_TEXT),clearText(),typeText(SEARCH_TEXT));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText("mytaxi demo")).check(matches(isEnabled()));
        onView(withText(SELECTED_DRIVER)).inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView())))).perform(click());

        onView(withId(R.id.textViewDriverName)).inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView())))).check(matches(withText(SELECTED_DRIVER)));
        onView(withId(R.id.fab)).perform(click());
    }

   @Test
    public void test003_Logout() {
        onView(withContentDescription("Open navigation drawer"))
                .perform(click());
       try {
           Thread.sleep(2000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
        onView(withId(R.id.design_menu_item_text))
               .perform(click());
    }

    public String fetchingLoginInfo(String seed, String key){
        try {
            final String RANDOM_USER_URL = "https://randomuser.me/api/";
            OkHttpClient mClient = new OkHttpClient();
            String url = RANDOM_USER_URL + "?seed=" + seed;
            Request request = new Request.Builder().url(url).build();
            Response r = mClient.newCall(request).execute();
            JsonParser mJsonParser = new JsonParser();
            JsonObject jsonObject = mJsonParser.parse(r.body().string()).getAsJsonObject();
            JsonArray results = jsonObject.getAsJsonArray("results");
            JsonElement jsonElement = results.get(0);
            JsonObject jsonUser = jsonElement.getAsJsonObject();
            JsonObject login = jsonUser.getAsJsonObject("login");
            return (null == login.get(key).getAsString() ? "" : login.get(key).getAsString());
        }
        catch(NullPointerException e) {
            System.out.println("No results for key and seed combination"+e);
            return "No Result";

        }

        catch(Exception e) {

            System.out.println("No results for key and seed combination"+e);
            return "No Results";

        }

    }

}