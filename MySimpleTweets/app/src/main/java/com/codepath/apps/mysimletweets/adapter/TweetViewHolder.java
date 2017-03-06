package com.codepath.apps.mysimletweets.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimletweets.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hongnhung on 05/03/2017.
 */

public class TweetViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_name)
    TextView mTvName;

    @BindView(R.id.img_avt)
    ImageView mImgAt;

    @BindView(R.id.tv_text_content)
    TextView mTvContent;

    @BindView(R.id.img_tweet)
    ImageView mImgTweet;



    public TweetViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
