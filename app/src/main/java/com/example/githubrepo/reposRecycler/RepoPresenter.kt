package com.example.githubrepo.reposRecycler


import android.content.ContentValues.TAG
import android.util.Log
import com.example.githubrepo.App
import com.example.githubrepo.Contract
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter

class RepoPresenter(val userRepo: UserRepoRecyclerInterface, val router: Router) :
    MvpPresenter<Contract.View>() {

    class RepoListPresenter : UserRepoPresenterInterface {
        val users = mutableListOf<Repo>()
        override var itemClickListener: ((RepoView) -> Unit)? = null

        override fun bindView(view: RepoView) {
            val user = users[view.pos]

            user.let {
                view.setRepo(user)
            }
        }

        override fun getCount(): Int {
            return users.size
        }
    }

    val repoListPresenter = RepoListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        listen()
    }

    private fun listen() {
        App.compositeDisposable.add(
            userRepo.getRepos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { it ->
                        repoListPresenter.itemClickListener =
                            { itemView ->
                                router.exit()
                                Log.i(TAG, "forks_count " + it[itemView.pos].forks_count?.toString())
                            }
                    },
                    { Log.i(TAG, "У нас случилась ошибка.") },
                )
        )
    }

    private fun loadData() {
        App.compositeDisposable.add(
            userRepo.getRepos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { it ->
                        repoListPresenter.users.addAll(it)
                        viewState.updateList()
                    },
                    { Log.i(TAG, "У нас случилась ошибка.") },
                )
        )
    }

    override fun onDestroy() {
        App.compositeDisposable.clear()
        super.onDestroy()
    }
}



