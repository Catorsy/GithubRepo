package com.example.githubrepo.interfaces

import com.example.githubrepo.retrofit.UserRetro
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProfileUserView : MvpView {
    fun setProfileData(user: UserRetro)
}