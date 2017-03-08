package com.codepath.apps.mysimletweets.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    private ComposeTweet handler;


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

        handler = new ComposeTweet() {
            @Override
            public void onPostedNewTweet() {
                mSearchRequest.reset();
                mList.clear();
                publishTimeline();
                mTweetArrayAdapter.notifyDataSetChanged();
                Log.e("nice", "ok");
            }
        };

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_time_line, menu);
        MenuItem itemPost = menu.findItem(R.id.actionSearch);
        MenuItem itemSeach = menu.findItem(R.id.actionPost);
        itemPost.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FragmentManager fm = getSupportFragmentManager();
                PostFragment postFragment = PostFragment.newInstance("");
                postFragment.show(fm, PostFragment.class.getSimpleName());
                postFragment.setHandler(handler);
                return false;
            }
        });



        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(itemSeach);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("text", query + "");
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void setUpView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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

    public interface ComposeTweet {
        void onPostedNewTweet();
    }
}

