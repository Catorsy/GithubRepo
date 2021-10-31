package com.example.githubrepo.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IDataSource {
    @GET("users")
    fun getUsers() : Single<List<UserRetro>>

    //для загрузки данных одного пользователя
    @GET("users/{login}")
    fun loadUser(@Path("login") login: String) : Single<UserRetro>
}