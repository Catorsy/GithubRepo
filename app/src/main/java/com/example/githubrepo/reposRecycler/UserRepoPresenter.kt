package com.example.githubrepo.reposRecycler

import android.content.ContentValues
import android.util.Log
import com.example.githubrepo.App
import com.example.githubrepo.interfaces.ItemView
import com.example.githubrepo.interfaces.ListPresenter
import com.example.githubrepo.interfaces.ProfileUserView
import com.example.githubrepo.retrofit.IGithubUsersRepo
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter

class UserRepoPresenter(val repo: UserRepoRecyclerInterface, val userString: String? = null,
) : MvpPresenter<RepoViewI>() {

    override fun onFirstViewAttach() {
        //с RX
        App.compositeDisposable.add(
            repo.getRepos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { it -> it.firstOrNull { it.login == userString }
                        .let {
                            viewState.setRepo(it!!)
                        }},
                    { Log.i(ContentValues.TAG, "У нас случилась ошибка.")},
                )
        )
    }
}