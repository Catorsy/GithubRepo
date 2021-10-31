package com.example.githubrepo.retrofit

import io.reactivex.schedulers.Schedulers


class RetrofitGithubUserRepo(val api: IDataSource) : IGithubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
}