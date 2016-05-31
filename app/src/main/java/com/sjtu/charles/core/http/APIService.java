package com.sjtu.charles.core.http;

import com.sjtu.charles.login.http.response.LolLoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by charles
 * Data: 2016/3/30.
 */
public interface APIService {
    @GET("login")
    Call<LolLoginResponse> lolLogin(@Query("username") String userName, @Query("password") String password);
}
