package com.example.githubrepo.ui

import com.example.githubrepo.Contract
import com.example.githubrepo.Screens
import com.example.githubrepo.interfaces.UserItemView
import com.example.githubrepo.interfaces.UserListPresenter
import com.example.githubrepo.model.GithubUserRepo
import com.example.githubrepo.model.User
import com.github.terrakok.cicerone.Router
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
        val users = userRepo.getUsers()
        usersListPresenter.itemClickListener =
            { itemView -> router.navigateTo(Screens.userProfileScreen(users[itemView.pos].id)) }
    }

    private fun loadData() {
        val users = userRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}


