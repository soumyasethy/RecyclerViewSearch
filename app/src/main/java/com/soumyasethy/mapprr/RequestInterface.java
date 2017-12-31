package com.soumyasethy.mapprr;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RequestInterface {

  @GET("/search/repositories?q=user:soumyasethy")
  Call<JSONResponse> getJSON();

  @GET("/search/repositories")
  Call<JSONResponse> getSearch(@Query("q") String user, @Query("sort") String sort,
                               @Query("order") String order);

  @GET
  Call<ResponseBody> getContributors(@Url String url);

}
