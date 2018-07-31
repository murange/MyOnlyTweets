package com.codepath.apps.restclienttemplate.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.codepath.apps.restclienttemplate.fragments.TweetsListFragment;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * create by rhu on 6/2/17
 */

public class MentionsTimelineFragment extends TweetsListFragment {
    TwitterClient client;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApp.getRestClient(null);
        populateTimeLine();
    }

    private void populateTimeLine() {
       client.getUserInfoTimeLine(new JsonHttpResponseHandler() {
                                        @Override
                                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                            Log.d("TwitterClient", response.toString());

                                        }

                                        @Override
                                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                                            // Log.d("TwitterClient" ,response.toString());
                                            TweetsListFragment fragmentTweetsList = null;
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
                                            Log.d("TwitterClient", errorResponse.toString());
                                            throwable.printStackTrace();
                }
                                    }

            );
        }
}
