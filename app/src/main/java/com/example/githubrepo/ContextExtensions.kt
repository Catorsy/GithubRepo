package com.example.githubrepo

import android.content.Context

val Context.app: App
    get() {
        return applicationContext as App
    }