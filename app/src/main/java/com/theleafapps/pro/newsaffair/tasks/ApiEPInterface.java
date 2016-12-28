package com.theleafapps.pro.newsaffair.tasks;

import com.theleafapps.pro.newsaffair.models.Source;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aviator on 28/12/16.
 */

public interface ApiEPInterface {
    @GET("v1/sources")
    Call<Source> getNewsSources(@Query("apiKey") String key);
}
