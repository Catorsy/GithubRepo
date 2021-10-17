package com.example.githubrepo.model

import com.example.githubrepo.App
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class GithubUserRepo {
    private val repositories = listOf(
        User(1234, "Jonny", null),
        User(456, "Fred", "https://github.com"),
        User(43, "Anna", null),
        User(444, "Elise", "https://github.com"),
        User(907, "John", null),
    )

    fun getUsers(): List<User> {
        return repositories
    }

    //для однократного получения данных нужно использовать Single, а для постоянного  - Observable.
    fun getUsersRx(): Single<List<User>> {
        return Single.just(repositories).delay(App.MY_DELAY, TimeUnit.SECONDS)
    }
}