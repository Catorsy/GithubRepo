package com.example.githubrepo.ui

import android.content.ContentValues.TAG
import android.util.Log
import com.example.githubrepo.App
import com.example.githubrepo.Contract
import com.example.githubrepo.Screens
import com.example.githubrepo.interfaces.UserItemView
import com.example.githubrepo.interfaces.UserListPresenter
import com.example.githubrepo.model.GithubUserRepo
import com.example.githubrepo.model.User
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter

class UsersPresenter(val userRepo: GithubUserRepo, val router: Router) :
    MvpPresenter<Contract.View>() {

    class UsersListPresenter : UserListPresenter {
        val users = mutableListOf<User>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            user.url?.let { view.setUrl(it) }
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
        //новый вариаант с RX
        App.compositeDisposable.add(
            userRepo.getUsersRx()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {it -> usersListPresenter.itemClickListener =
                        { itemView -> router.navigateTo(Screens.userProfileScreen(it[itemView.pos].id)) }},
                    {Log.i(TAG, "У нас случилась ошибка.")},
                        )
        )

        //старый вариант
//        val users = userRepo.getUsers()
//        usersListPresenter.itemClickListener =
//        { itemView -> router.navigateTo(Screens.userProfileScreen(users[itemView.pos].id)) }
    }

    private fun loadData() {
        //новый вариаант с RX
        App.compositeDisposable.add(userRepo.getUsersRx()
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
        App.compositeDisposable.dispose()
        super.onDestroy()
    }
}


