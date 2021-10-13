package com.example.githubrepo.model

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
}