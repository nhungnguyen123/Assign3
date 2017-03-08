package com.codepath.apps.mysimletweets.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.codepath.apps.mysimletweets.R;
import com.codepath.apps.mysimletweets.models.Tweet;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_detail_id)
    TextView mTvdetail;


    private Intent detailIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Tweet tweet = (Tweet) getIntent().getParcelableExtra("detail");
        Log.e("tweet", tweet.getId() + "");
        mTvdetail.setText(tweet.getId() + "");
    }
}
