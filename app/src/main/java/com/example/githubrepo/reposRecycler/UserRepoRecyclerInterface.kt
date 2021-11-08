package com.example.githubrepo.reposRecycler

import io.reactivex.Single

interface UserRepoRecyclerInterface {
    fun getRepos(): Single<List<Repo>>
}