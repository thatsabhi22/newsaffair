package com.theleafapps.pro.newsaffair.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.theleafapps.pro.newsaffair.R;
import com.theleafapps.pro.newsaffair.models.Source;
import com.theleafapps.pro.newsaffair.tasks.ApiEPInterface;

import java.io.IOException;

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

    @BindView(R.id.dissu) TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                text.setText(response.message());
            }

            @Override
            public void onFailure(Call<Source> call, Throwable t) {
                text.setText(t.getMessage());
            }
        });
    }
}
