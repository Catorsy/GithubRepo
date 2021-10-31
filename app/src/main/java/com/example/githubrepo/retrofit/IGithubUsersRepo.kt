package com.example.githubrepo.retrofit

import io.reactivex.Single


interface IGithubUsersRepo {
    fun getUsers(): Single<List<UserRetro>>
}