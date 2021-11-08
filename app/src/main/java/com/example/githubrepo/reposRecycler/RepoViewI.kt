package com.example.githubrepo.reposRecycler

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoViewI: MvpView {
    fun setRepo(repo: Repo)
}