package com.aplication.material.sortumanen.retrofit;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

interface GithubService {
    @GET("users/{user}")
    Observable<Github> getGithubUser(@Path("username") String user);
}
