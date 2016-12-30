package com.theleafapps.pro.newsaffair.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.theleafapps.pro.newsaffair.R;
import com.theleafapps.pro.newsaffair.adapters.SourcesGridViewRecyclerAdapter;
import com.theleafapps.pro.newsaffair.models.Source;
import com.theleafapps.pro.newsaffair.tasks.ApiEPInterface;

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

public class GridActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://newsapi.org/";

    @BindView(R.id.sources_recycler_view)
    RecyclerView sourcesRecyclerView;

    @BindView(R.id.no_network_label_1)
    TextView no_network_label_1;

    @BindView(R.id.no_network_label_2)
    TextView no_network_label_2;

    @BindView(R.id.refresh_icon)
    ImageView refresh_icon;

    @BindView(R.id.no_network_image_icon)
    ImageView no_network_image_icon;


    StaggeredGridLayoutManager staggeredGridLayoutManager;
    SourcesGridViewRecyclerAdapter sourcesGridViewRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ButterKnife.bind(this);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEPInterface service = retrofit.create(ApiEPInterface.class);

        Call<Source> call = service.getNewsSources(getResources().getString(R.string.apkky));
        call.enqueue(new Callback<Source>() {
            @Override
            public void onResponse(Call<Source> call, retrofit2.Response<Source> response) {
//                Log.d("Tangho", " response"+response.body().getSources().get(0));

                List<Source.SourcesBean> sourceList = response.body().getSources();

                if(response.body().getSources().size()>0){
                    sourcesGridViewRecyclerAdapter = new SourcesGridViewRecyclerAdapter(GridActivity.this,
                            sourceList);
                    sourcesRecyclerView.setAdapter(sourcesGridViewRecyclerAdapter);

                }
            }

            @Override
            public void onFailure(Call<Source> call, Throwable t) {

                sourcesRecyclerView.setVisibility(View.INVISIBLE);
                no_network_image_icon.setVisibility(View.VISIBLE);
                refresh_icon.setVisibility(View.VISIBLE);
                no_network_label_1.setVisibility(View.VISIBLE);
                no_network_label_2.setVisibility(View.VISIBLE);
//              text.setText(t.getMessage());

            }
        });

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
        sourcesRecyclerView.setLayoutManager(staggeredGridLayoutManager);
    }
}
