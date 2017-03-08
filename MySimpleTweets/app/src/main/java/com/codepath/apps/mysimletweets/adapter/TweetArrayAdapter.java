package com.codepath.apps.mysimletweets.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.codepath.apps.mysimletweets.R;
import com.codepath.apps.mysimletweets.models.Tweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by hongnhung on 05/03/2017.
 */

public class TweetArrayAdapter extends RecyclerView.Adapter {


    private ListenTweet mListenTweet;

    public ListenTweet getmListenTweet() {
        return mListenTweet;
    }

    public void setmListenTweet(ListenTweet mListenTweet) {
        this.mListenTweet = mListenTweet;
    }

    private List<Tweet> mList;
    private Context mContext;

    public TweetArrayAdapter(List<Tweet> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }


    public void addTweet(Tweet tweet) {
        mList.add(tweet);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tweet, null);

        return new TweetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        Tweet tweet = mList.get(position);
        TweetViewHolder tweetViewHolder = (TweetViewHolder) holder;
        tweetViewHolder.mTvName.setText(tweet.getUser().getName());
        tweetViewHolder.mTvContent.setText(tweet.getText());
        tweetViewHolder.mTvTime.setText(tweet.getRelativeTimeAgo());

        tweetViewHolder.mImgTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click icon", "ok");
                mListenTweet.showDetail(mList.get(position));
            }
        });

        Log.e("time", tweet.getRelativeTimeAgo());

        if (tweet.getUser().getProfileImage() == null) {
            Log.e("getProImaUrl", "null");
        } else {

            Glide.with(mContext)
                    .load(tweet.getUser().getProfileImage())
                    .bitmapTransform(new RoundedCornersTransformation(mContext, 30, 2))
                    .animate(android.R.anim.slide_out_right)
                    .into(tweetViewHolder.mImgAt);
        }
        if (tweet.getProfileImageUrl() == null) {
            Log.e("geImageUrl", "null");

        } else {
            {
                Glide.with(mContext)
                        .load(tweet.getProfileImageUrl())
                        .into(tweetViewHolder.mImgTweet);
            }
        }

        if (position == mList.size() - 1) {
            Log.e("last", position + "");
            mListenTweet.loadMore();

        }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
