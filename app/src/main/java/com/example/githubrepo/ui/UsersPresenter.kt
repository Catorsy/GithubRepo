package com.example.githubrepo.ui

import android.content.ContentValues.TAG
import android.util.Log
import com.example.githubrepo.App
import com.example.githubrepo.Contract
import com.example.githubrepo.Screens
import com.example.githubrepo.interfaces.UserItemView
import com.example.githubrepo.interfaces.UserListPresenter
import com.example.githubrepo.retrofit.IGithubUsersRepo
import com.example.githubrepo.retrofit.UserRetro
import com.github.terrakok.cicerone.Router
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter

class UsersPresenter(val userRepo: IGithubUsersRepo, val router: Router) :
    MvpPresenter<Contract.View>() {

    class UsersListPresenter: UserListPresenter {
        val users = mutableListOf<UserRetro>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let {
                view.setLogin(it)
            }
            user.avatarUrl?.let {
                view.loadAvatar(it)
            }
        }

        override fun getCount(): Int {
            return users.size
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        listen()
    }

    private fun listen() {
        //новый вариант с RX
        App.compositeDisposable.add(
            userRepo.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {it -> usersListPresenter.itemClickListener =
                        { itemView -> router.navigateTo(Screens.userRetroInfo(it[itemView.pos].login)) }},
                    {Log.i(TAG, "У нас случилась ошибка.")},
                        )
        )

        //старый вариант
//        val users = userRepo.getUsers()
//        usersListPresenter.itemClickListener =
//        { itemView -> router.navigateTo(Screens.userProfileScreen(users[itemView.pos].id)) }
    }

    private fun loadData() {
//        App.compositeDisposable.add(userRepo.getUsers()
//            .observeOn(uiScheduler)
//            .subscribe({ repos ->
//                usersListPresenter.users.clear()
//                usersListPresenter.users.addAll(repos)
//                viewState.updateList()
//            },
//                {Log.i(TAG, "У нас случилась ошибка.")},
//                ))

        //новый вариант с RX
        App.compositeDisposable.add(userRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({it -> usersListPresenter.users.addAll(it)
                viewState.updateList()},
                {Log.i(TAG, "У нас случилась ошибка.")},
            ))

        //старый вариант
//        val users = userRepo.getUsers()
//        usersListPresenter.users.addAll(users)
//        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        App.compositeDisposable.clear()
        super.onDestroy()
    }
}


