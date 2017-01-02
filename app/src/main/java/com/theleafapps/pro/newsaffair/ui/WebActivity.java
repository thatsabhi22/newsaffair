package com.theleafapps.pro.newsaffair.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.theleafapps.pro.newsaffair.R;
import com.theleafapps.pro.newsaffair.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends BaseActivity {

    @BindView(R.id.news_web_view)
    WebView news_web_view;

    Intent receiveI;
    String articleUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        receiveI    =   getIntent();
        articleUrl  =   receiveI.getStringExtra("newsArticleUrl");

        if(!TextUtils.isEmpty(articleUrl)) {
            news_web_view.setWebViewClient(new MyWebViewClient());
            news_web_view.getSettings().setJavaScriptEnabled(true);
            news_web_view.getSettings().setSupportZoom(true);
            news_web_view.getSettings().setBuiltInZoomControls(true);
            news_web_view.loadUrl(articleUrl);
            news_web_view.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.getSettings().setSupportZoom(true);
            view.loadUrl(url);
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean providesActivityToolbar() {
        return false;
    }
}
