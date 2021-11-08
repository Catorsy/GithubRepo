package com.example.githubrepo.loadImages

interface ImageLoaderGlide<T> {
    fun loadInto(url: String, container: T)
}