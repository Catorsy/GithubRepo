package com.example.githubrepo

import android.app.Application
import androidx.fragment.app.Fragment
import com.example.githubrepo.model.GithubUserRepo
import com.github.terrakok.cicerone.Cicerone
import io.reactivex.disposables.CompositeDisposable

class App : Application() {
    companion object {
        val userRepo = GithubUserRepo()
        private val cicerone = Cicerone.create()
        val router get() = cicerone.router
        val navigatorHolder get() = cicerone.getNavigatorHolder() //это чтобы с фрагментами работать можно было
        var compositeDisposable: CompositeDisposable = CompositeDisposable()
        const val MY_DELAY = 3L
        const val MESSAGE_KEY = "message"
    }

    val eventBus = EventBus()
}

val Fragment.app: App
    get() = requireContext().applicationContext as App