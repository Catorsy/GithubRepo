package com.example.githubrepo.reposRecycler

import com.google.gson.annotations.Expose

data class Repo(
    val login: String?,
    @Expose val name: String? = null,
    @Expose val size: Int? = null,
    @Expose val language: String? = null,
    @Expose val forks_count: Int? = null,
)
