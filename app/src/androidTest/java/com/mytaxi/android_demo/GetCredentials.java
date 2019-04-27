package com.mytaxi.android_demo;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import junit.framework.Assert;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetCredentials {

    public String getValue(String key) {
        String seed = "a1f30d446f820665";
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
        } catch (NullPointerException e) {
            Assert.fail("No result is displayed for request key");
            return "";
        } catch (Exception e) {
            Assert.fail("Exception occurred");
            return "";
        }

    }
}