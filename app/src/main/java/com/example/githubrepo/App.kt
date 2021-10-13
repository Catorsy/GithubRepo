package com.example.githubrepo

import android.app.Application
import com.example.githubrepo.model.GithubUserRepo
import com.github.terrakok.cicerone.Cicerone

class App : Application() {

    companion object {
        val userRepo = GithubUserRepo()
        private val cicerone = Cicerone.create()
        val router get() = cicerone.router
        val navigatorHolder get() = cicerone.getNavigatorHolder() //это чтобы с фрагментами работать можно было
    }
}