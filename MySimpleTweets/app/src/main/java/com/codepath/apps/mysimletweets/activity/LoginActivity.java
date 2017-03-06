package com.codepath.apps.mysimletweets.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.codepath.apps.mysimletweets.R;
import com.codepath.apps.mysimletweets.TwitterApplication;
import com.codepath.apps.mysimletweets.Twitterclient;
import com.codepath.apps.mysimletweets.models.Tweet;
import com.codepath.oauth.OAuthLoginActionBarActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends OAuthLoginActionBarActivity<Twitterclient> {

    private Gson mGson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mGson = new Gson();
    }


    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    // OAuth authenticated successfully, launch primary authenticated activity
    // i.e Display application "homepage"
    @Override
    public void onLoginSuccess() {

        Intent i = new Intent(this, TimelineActivity.class);
        startActivity(i);
        finish();
        Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
    }

    // OAuth authentication flow failed, handle the error
    // i.e Display an error dialog or toast
    @Override
    public void onLoginFailure(Exception e) {
        e.printStackTrace();
    }

    // Click handler method for the button used to start OAuth flow
    // Uses the client to initiate OAuth authorization
    // This should be tied to a button used to login
    public void loginToRest(View view) {
        Log.e("click", "ok");
        getClient().connect();
    }


//    public void getHomeTimeline(int page, AsyncHttpResponseHandler handler) {
//        String apiUrl = getApiUrl("statuses/home_timeline.json");
//        RequestParams params = new RequestParams();
//        params.put("page", String.valueOf(page));
//        getClient().get(apiUrl, params, handler);
//    }

}
