package com.example.githubrepo.reposRecycler

import com.example.githubrepo.retrofit.IDataSource
import com.example.githubrepo.retrofit.IGithubUsersRepo
import io.reactivex.schedulers.Schedulers

class RetrofitGithubRepoRepo(val api: IDataSource, val login: String) : UserRepoRecyclerInterface {
    override fun getRepos() = api.getRepos(login).subscribeOn(Schedulers.io())
}