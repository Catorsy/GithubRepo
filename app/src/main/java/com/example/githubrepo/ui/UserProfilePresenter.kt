package com.example.githubrepo.ui

import android.content.ContentValues
import android.util.Log
import com.example.githubrepo.App
import com.example.githubrepo.interfaces.ProfileUserView
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter

class UserProfilePresenter(val userId: Int? = null,
    val router: Router) : MvpPresenter<ProfileUserView>() {

    override fun onFirstViewAttach() {
        //с RX
        App.compositeDisposable.add(
            App.userRepo.getUsersRx()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { it -> it.firstOrNull { it.id == userId }
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