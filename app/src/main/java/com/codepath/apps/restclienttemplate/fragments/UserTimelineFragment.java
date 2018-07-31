package com.codepath.apps.restclienttemplate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.github.scribejava.apis.TwitterApi;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

//import static com.loopj.android.http.AsyncHttpClient.log;

/**
 * created by rhu 28/07/18
 */

public class UserTimelineFragment extends TweetsListFragment {
    TwitterClient client;
    public  static UserTimelineFragment newInstance(String  screenName){
        UserTimelineFragment userTimelineFragment = new UserTimelineFragment();
        Bundle args =  new Bundle( );
        args.putString("screen_name", screenName);
        userTimelineFragment.setArguments(args);
        return userTimelineFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApp.getRestClient(null);
        populateTimeline();
    }

    private void populateTimeline() {
        String screenName = getArguments().getString("screen_name");
        client.getUserInfoTimeLine( new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        Log.d("TwitterClient", response.toString());

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        // Log.d("TwitterClient" ,response.toString());
                        addItems(response);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.d("TwitterClient", responseString);
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        Log.d("TwitterClient", errorResponse.toString());
                        throwable.printStackTrace();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                        log.d("TwitterClient", errorResponse.toString());
                        throwable.printStackTrace();

                    }

                }
        );
    }
}


        // comes from the activity


