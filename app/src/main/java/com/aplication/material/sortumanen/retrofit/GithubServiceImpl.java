package com.aplication.material.sortumanen.retrofit;


import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GithubServiceImpl {
    private static final String TAG = "GithubServiceImpl";

    public void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .build();

        GithubService githubService = retrofit.create(GithubService.class);
        Observable<Github> githubUser = githubService.getGithubUser("dadoz");

        githubUser.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> { Log.e(TAG, o.toString()); });

    }
}
