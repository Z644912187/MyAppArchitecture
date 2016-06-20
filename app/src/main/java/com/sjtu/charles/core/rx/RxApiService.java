package com.sjtu.charles.core.rx;

import com.sjtu.charles.login.http.response.LolLoginResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhuyifei on 2016/6/20.
 */
public interface RxApiService {

    /**
     * 登录
     *
     * @return
     */
    @GET("materials/{id}/cover")
    Observable<LolLoginResponse> lolLogin(@Query("username") String userName, @Query("password") String password);
}
