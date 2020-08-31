package com.uerbeautybusiness.uersbeauty.net;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.uerbeautybusiness.uersbeauty.constants.URLs;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 第一步构建retrofit
 * Retrofit
 * Created by HanXin on 16/2/22.
 */
public class MyRetrofit {

    private static final String TAG = "Service";
    private static Retrofit.Builder retrofitBuilder;
    private OkHttpClient sOkHttpClient;
    public static MyRetrofit myRetrofit;
    private static Object INTANSE_OBJECT = new Object();
    private long DEFAULT_TIMEOUT = 30;

    public static MyRetrofit getIntanse() {
        if (myRetrofit == null) {
            synchronized (INTANSE_OBJECT) {
                if (myRetrofit == null) {
                    myRetrofit = new MyRetrofit();
                }
            }
        }
        return myRetrofit;
    }

    final static Gson gson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss SSS")
        .serializeNulls()
        .create();

    //重写okhttp
    public MyRetrofit() {
        //实例化这个对象是Retrofit+Rxjava
        //retrofit底层用的okHttp,所以设置超时还需要okHttp
        //然后设置5秒超时
        //其中DEFAULT_TIMEOUT是我这边定义的一个常量
        //TimeUnit为java.util.concurrent包下的时间单位
        //TimeUnit.SECONDS这里为秒的单位
//        OkHttpClient client = new OkHttpClient.Builder()
//            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//            .build();
        sOkHttpClient = createOkHttpClient();
        retrofitBuilder = new Retrofit.Builder()
            .baseUrl(URLs.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(sOkHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());//创建一个retrofit对象

    }


    public static <S> S createService(Class<S> serviceClass) {


        Retrofit retrofit;

        retrofit = getIntanse().getRetrofit();

        return retrofit.create(serviceClass);
    }

    private Retrofit getRetrofit() {
        return retrofitBuilder.client(sOkHttpClient).build();
    }

    public static <S> S createService(Class<S> serviceClass, String url) {

        Retrofit retrofit;

        retrofit = getIntanse().getRetrofit(url);

        return retrofit.create(serviceClass);

    }

    private Retrofit getRetrofit(String url) {
        return retrofitBuilder.baseUrl(url).client(sOkHttpClient).build();
    }

    private OkHttpClient createOkHttpClient() {

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);


        //在这里可以设置在httpClient的拦截log
//        httpClientBuilder.addInterceptor(new LoggingInterceptor());
//        httpClientBuilder.addInterceptor(new SealAccountInterceptor());


        try {

            SSLContext sslContext = SSLContext.getInstance("TLS");
            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

            };

            sslContext.init(null, new TrustManager[] {trustManager}, new SecureRandom());
            httpClientBuilder.sslSocketFactory(sslContext.getSocketFactory());

        } catch (NoSuchAlgorithmException e) {
            Log.i(TAG, e.getMessage());
        } catch (KeyManagementException e) {
            Log.i(TAG, e.getMessage());
        }

        httpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        OkHttpClient client = httpClientBuilder.build();
        Log.i("DMclient---->", client.toString());

        return client;
    }


}
