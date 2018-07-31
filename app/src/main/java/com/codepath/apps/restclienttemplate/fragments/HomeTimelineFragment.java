package com.codepath.apps.restclienttemplate.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

/**
 * create by rhu on 6/2/17
 */

public class HomeTimelineFragment extends TweetsListFragment {
    private Context getContext;
    TwitterClient  client = TwitterApp.getRestClient(getContext);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        popuLateTimeLine();
    }

    private void popuLateTimeLine(){
            client.getHomeTimeLine(new JsonHttpResponseHandler() {
                                       @Override
                                       public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                           Log.d("TwitterClient", response.toString());

                                       }

                                       @Override
                                       public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                                            Log.d("TwitterClient" ,response.toString());
                                          System.out.println("TWEET: "+ response.toString());
                                           addItems(response);
                                       }

                                       @Override
                                       public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

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






