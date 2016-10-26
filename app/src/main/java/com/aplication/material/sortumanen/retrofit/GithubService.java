package com.aplication.material.sortumanen.retrofit;

import com.aplication.material.sortumanen.models.Github;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

interface GithubService {
    @GET("users/{username}")
    Observable<Github> getGithubUser(@Path("username") String username);
}
