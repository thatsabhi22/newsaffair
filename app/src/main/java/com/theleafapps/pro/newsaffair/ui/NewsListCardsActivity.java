package com.theleafapps.pro.newsaffair.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;

import com.theleafapps.pro.newsaffair.R;
import com.theleafapps.pro.newsaffair.adapters.NewsListViewRecyclerAdapter;
import com.theleafapps.pro.newsaffair.models.Article;
import com.theleafapps.pro.newsaffair.tasks.ApiEPInterface;
import com.theleafapps.pro.newsaffair.ui.base.BaseActivity;
import com.theleafapps.pro.newsaffair.utils.Commons;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsListCardsActivity extends BaseActivity {

    @BindView(R.id.news_list_recycler_view)
    RecyclerView newsListRecyclerView;

    NewsListViewRecyclerAdapter newsListViewRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;
    OkHttpClient okHttpClient;
    Intent receiveI;
    String sourceId, sourceName;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list_cards);
        ButterKnife.bind(this);

        setupToolbar();

        receiveI = getIntent();
        sourceId = receiveI.getStringExtra("sourceId");
        sourceName = receiveI.getStringExtra("sourceName");

        if (!TextUtils.isEmpty(sourceName))
            NewsListCardsActivity.this.setTitle("News Affair - " + sourceName);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("  The fresh news is cooking ...");
        pDialog.setCancelable(false);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        Commons.showpDialog(pDialog);

//################################################
//
//      Implementing OKHttpClient
//
//################################################

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("Accept", "Application/JSON").build();
//                        Response response = chain.proceed(request);
//                        Log.w("Tangho", response.body().string());
//                        return response;
                        return chain.proceed(request);
                    }
                }).build();

//################################################bqa
//
//      Implementing Retrofit
//
//################################################

//###### Building Retrofit Object ################

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Commons.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//################################################

        ApiEPInterface service = retrofit.create(ApiEPInterface.class);

//############### Calling Service ################

        if (!TextUtils.isEmpty(sourceId)) {
            Call<Article> call = service.getNewsListBySourceId(sourceId,
                    getResources().getString(R.string.apkky));
            call.enqueue(new Callback<Article>() {
                @Override
                public void onResponse(Call<Article> call, retrofit2.Response<Article> response) {
//              Log.d("Tangho", " response"+response.body().getSources().get(0));

                    List<Article.ArticlesBean> newsList = response.body().getArticles();
                    if (response.body().getArticles().size() > 0) {
                        newsListViewRecyclerAdapter = new NewsListViewRecyclerAdapter(NewsListCardsActivity.this,
                                newsList);
                        newsListRecyclerView.setAdapter(newsListViewRecyclerAdapter);
                    }
                    Commons.hidepDialog(pDialog);
                }

                @Override
                public void onFailure(Call<Article> call, Throwable t) {
                    Commons.hidepDialog(pDialog);
                    Intent intent1 = new Intent(NewsListCardsActivity.this, NoNetworkActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent1);
                }
            });

        }

//#################################################
//
//      Implementing RecyclerView
//
//################################################

        newsListRecyclerView.setLayoutManager(linearLayoutManager);
    }


    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                openDrawer();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean providesActivityToolbar() {
        return true;
    }
}
