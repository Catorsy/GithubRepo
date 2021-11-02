package com.example.githubrepo

import android.app.Application
import androidx.fragment.app.Fragment
import com.example.githubrepo.model.GithubUserRepo
import com.example.githubrepo.retrofit.IDataSource
import com.example.githubrepo.retrofit.RetrofitGithubUserRepo
import com.github.terrakok.cicerone.Cicerone
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    companion object {
        val userRepo = GithubUserRepo()
        private val cicerone = Cicerone.create()
        val router get() = cicerone.router
        val navigatorHolder get() = cicerone.getNavigatorHolder() //это чтобы с фрагментами работать можно было
        var compositeDisposable: CompositeDisposable = CompositeDisposable()
        const val MY_DELAY = 3L

        private val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES) //avatar_url = avatarUrl.
            .excludeFieldsWithoutExposeAnnotation() //кто не expose, игнорируем
            .create()

        private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //Single возвращать
            .addConverterFactory(GsonConverterFactory.create(gson)) //преобразует из json в user
            .build()

        val api = retrofit.create(IDataSource::class.java)
    }

    val eventBus = EventBus()
}

val Fragment.app: App
    get() = requireContext().applicationContext as App
