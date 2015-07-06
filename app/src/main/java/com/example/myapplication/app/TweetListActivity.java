package com.example.myapplication.app;

import android.app.ListActivity;
import android.os.Bundle;
import com.twitter.sdk.android.core.*;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import com.twitter.sdk.android.tweetui.TweetViewFetchAdapter;

import java.util.List;

/**
 * Created by user on 2015/07/06.
 */
public class TweetListActivity extends ListActivity {

    final TweetViewFetchAdapter adapter =
            new TweetViewFetchAdapter<CompactTweetView>(
                    TweetListActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet_list);

        setListAdapter(adapter);

        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService  statusesService  = twitterApiClient.getStatusesService();
        statusesService.homeTimeline(50, null, null, false, false, false, false,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> listResult) {
                        adapter.setTweets(listResult.data);
                    }

                    @Override
                    public void failure(TwitterException e) {

                    }
                });
    }
}

