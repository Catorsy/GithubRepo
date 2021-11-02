package com.example.githubrepo.model

import com.example.githubrepo.App
import com.example.githubrepo.retrofit.IGithubUsersRepo
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class GithubUserRepo {
    private val repositories = listOf(
        User(1234, "Jonny", null, 0),
        User(456, "Fred", "https://github.com", 2),
        User(43, "Anna", null, 0),
        User(444, "Elise", "https://github.com", 0),
        User(907, "John", null, 1),
    )

    fun getUsers(): List<User> {
        return repositories
    }

    //для однократного получения данных нужно использовать Single, а для постоянного  - Observable.
    fun getUsersRx(): Single<List<User>> {
        return Single.just(repositories).delay(App.MY_DELAY, TimeUnit.SECONDS)
    }
}