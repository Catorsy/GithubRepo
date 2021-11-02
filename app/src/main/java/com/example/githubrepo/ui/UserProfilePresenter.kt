package com.example.githubrepo.ui

import android.content.ContentValues
import android.util.Log
import com.example.githubrepo.App
import com.example.githubrepo.App.Companion.userRepo
import com.example.githubrepo.interfaces.ProfileUserView
import com.example.githubrepo.retrofit.IGithubUsersRepo
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter

class UserProfilePresenter(val userRepo: IGithubUsersRepo, val userString: String? = null,
                           val router: Router) : MvpPresenter<ProfileUserView>() {

    override fun onFirstViewAttach() {
        //с RX
        App.compositeDisposable.add(
            userRepo.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { it -> it.firstOrNull { it.login == userString }
                        .let {
                            viewState.setProfileData(it!!)
                        }},
                    {Log.i(ContentValues.TAG, "У нас случилась ошибка.")},
                )
        )

        //старый
//        val currentUser = App.userRepo.getUsers().firstOrNull { it.id == userId }
//        currentUser?.let {
//            viewState.setProfileData(it)
//        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}