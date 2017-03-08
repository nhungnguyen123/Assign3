package com.codepath.apps.mysimletweets.adapter;

import com.codepath.apps.mysimletweets.models.Tweet;
import com.codepath.apps.mysimletweets.models.User;

/**
 * Created by hongnhung on 05/03/2017.
 */

public interface ListenTweet {
    void loadMore();

    void showDetail(Tweet user);
}
