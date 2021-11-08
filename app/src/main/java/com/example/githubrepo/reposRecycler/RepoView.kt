package com.example.githubrepo.reposRecycler

import com.example.githubrepo.interfaces.ItemView


interface RepoView : ItemView {
    fun setRepo(repo: Repo)
}