package com.example.githubrepo.reposRecycler

import android.content.ContentValues
import android.util.Log
import com.example.githubrepo.App
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