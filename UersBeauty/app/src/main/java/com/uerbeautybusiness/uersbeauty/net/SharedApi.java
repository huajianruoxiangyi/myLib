package com.uerbeautybusiness.uersbeauty.net;


import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by guyao on 18/5/8.
 * /**
 * 公共网络接口
 */
public interface SharedApi {

    //运动记录接口
    @FormUrlEncoded
    @POST("person/Lol/motionRecord")
    Observable<JsonObject> requestSportRecord(@Field("uid") String uid);

}
