package com.codepath.apps.mysimletweets.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.codepath.apps.mysimletweets.R;
import com.codepath.apps.mysimletweets.TwitterApplication;
import com.codepath.apps.mysimletweets.Twitterclient;
import com.codepath.apps.mysimletweets.adapter.ListenTweet;
import com.codepath.apps.mysimletweets.adapter.TweetArrayAdapter;
import com.codepath.apps.mysimletweets.models.SearchRequest;
import com.codepath.apps.mysimletweets.models.Tweet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {

    private Twitterclient twitterclient;
    private List<Tweet> mList;
    StaggeredGridLayoutManager gridLayoutManager;
    private SearchRequest mSearchRequest = new SearchRequest();

    @BindView(R.id.lv_tw)
    RecyclerView rvTw;

    TweetArrayAdapter mTweetArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        ButterKnife.bind(this);
        mList = new ArrayList<>();
        twitterclient = new Twitterclient(TimelineActivity.this);
        setUpView();
        publishTimeline();


    }


    public void setUpView() {
        gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rvTw.setHasFixedSize(true);
        rvTw.setLayoutManager(gridLayoutManager);
        mTweetArrayAdapter = new TweetArrayAdapter(mList, TimelineActivity.this);
        rvTw.setAdapter(mTweetArrayAdapter);
        mTweetArrayAdapter.setmListenTweet(new ListenTweet() {
            @Override
            public void loadMore() {
                publishTimeline();
            }
        });

    }

    private void publishTimeline() {
        twitterclient.getHomeTimeline(mSearchRequest.nextPage(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                List<Tweet> tweets = new Gson().fromJson(response.toString(), new TypeToken<List<Tweet>>() {
                }.getType());

                if (mList == null) {
                    mList.addAll(tweets);

                } else {
                    mList.addAll(mList.size(), tweets);
                }
                Log.e("sizepage", mList.size() + "");
                Log.e("json1", new Gson().toJson(tweets.get(0)));
                mTweetArrayAdapter.notifyDataSetChanged();

            }

        });
    }
}

