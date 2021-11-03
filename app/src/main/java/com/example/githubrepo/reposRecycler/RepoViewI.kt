package com.example.githubrepo.reposRecycler

import com.example.githubrepo.retrofit.UserRetro
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoViewI: MvpView {
    fun setRepo(repo: Repo)
}