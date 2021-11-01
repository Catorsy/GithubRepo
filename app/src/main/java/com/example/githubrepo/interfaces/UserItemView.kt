package com.example.githubrepo.interfaces

interface UserItemView: ItemView {
    fun setLogin(text: String)
    fun setUrl(text: String)
    fun loadAvatar(url: String)
}