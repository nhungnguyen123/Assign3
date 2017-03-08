package com.codepath.apps.mysimletweets.activity;

import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.mysimletweets.R;
import com.codepath.apps.mysimletweets.Twitterclient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

public class PostFragment extends DialogFragment {
    public static final String IMG_URL_AVT = "imgUrlProfile";
    public static final int MAX_LENGTH = 140;

    @BindView(R.id.btn_post)
    Button mBtnPost;

    @BindView(R.id.edt_content_post)
    EditText mEdtContent;

    @BindView(R.id.textView)
    TextView mTvChange;


    Twitterclient twitterclient;
    String content;

    private TimelineActivity.ComposeTweet handler;

    public static PostFragment newInstance(String imgUrlProfile) {
        PostFragment frag = new PostFragment();
        Bundle args = new Bundle();
        args.putString(IMG_URL_AVT, imgUrlProfile);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_post, container, false);

        ButterKnife.bind(this, view);
        return view;
//        ButterKnife.bind(this, binding.getRoot());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        twitterclient = new Twitterclient(getActivity());
        mEdtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int lengthTyped = MAX_LENGTH - s.length();
                mTvChange.setText(String.valueOf(lengthTyped));
                mBtnPost.setEnabled(lengthTyped >= 0 && lengthTyped < MAX_LENGTH);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setHandler(TimelineActivity.ComposeTweet handler) {
        this.handler = handler;
    }
//
//    @Override
//    protected void onCreateV(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_post);
//        ButterKnife.bind(this);
//        twitterclient = new Twitterclient(PostFragment.this);
//        OnClickPost();
//    }

    @OnClick(R.id.btn_post)
    public void OnClickPost() {
        content = mEdtContent.getText().toString();
        setPost(content);
    }

    public void setPost(String content) {
        twitterclient.postTweet(content, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("success", "OK");
                dismiss();
                if (handler != null) {
                    handler.onPostedNewTweet();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("error", "err");
            }
        });

    }
}
