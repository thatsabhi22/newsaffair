package com.theleafapps.pro.newsaffair.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;

import com.theleafapps.pro.newsaffair.R;
import com.theleafapps.pro.newsaffair.adapters.NewsSourcesGridViewRecyclerAdapter;
import com.theleafapps.pro.newsaffair.models.Source;
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

public class NewsSourceGridActivity extends BaseActivity {

    @BindView(R.id.sources_recycler_view)
    RecyclerView sourcesRecyclerView;

    StaggeredGridLayoutManager staggeredGridLayoutManager;
    NewsSourcesGridViewRecyclerAdapter sourcesGridViewRecyclerAdapter;
    private ProgressDialog pDialog;
    OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_source_grid);
        ButterKnife.bind(this);

        setupToolbar();

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("  The fresh news is cooking ...");
        pDialog.setCancelable(false);

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
                                .addHeader("Accept","Application/JSON").build();
//                        Response response = chain.proceed(request);
//                        Log.w("Tangho", response.body().string());
//                        return response;
                        return chain.proceed(request);
                    }
                }).build();


//################################################
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

        Call<Source> call = service.getNewsSources(getResources().getString(R.string.apkky));
        call.enqueue(new Callback<Source>() {
            @Override
            public void onResponse(Call<Source> call, retrofit2.Response<Source> response) {
//              Log.d("Tangho", " response"+response.body().getSources().get(0));

                List<Source.SourcesBean> sourceList = response.body().getSources();
                if(response.body().getSources().size()>0){
                    sourcesGridViewRecyclerAdapter = new NewsSourcesGridViewRecyclerAdapter(NewsSourceGridActivity.this,
                            sourceList);
                    sourcesRecyclerView.setAdapter(sourcesGridViewRecyclerAdapter);
                }
                Commons.hidepDialog(pDialog);
            }

            @Override
            public void onFailure(Call<Source> call, Throwable t) {
                Commons.hidepDialog(pDialog);
                Intent intent1 = new Intent(NewsSourceGridActivity.this,NoNetworkActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }
        });

//#################################################


//################################################
//
//      Implementing RecyclerView with StaggeredGridView
//
//################################################


        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        sourcesRecyclerView.setLayoutManager(staggeredGridLayoutManager);
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
