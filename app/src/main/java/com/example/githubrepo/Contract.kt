package com.example.githubrepo

import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

class Contract {

    @StateStrategyType(AddToEndSingleStrategy::class)
    interface View: MvpView {
        fun init()
        fun updateList()
    }

    @StateStrategyType(AddToEndSingleStrategy::class)
    interface MainView : MvpView

    abstract class Presenter: MvpPresenter<View>(){
        // ??? А если делаем на куче интерфейсов, в контракт стоит помещать их?
        //форма контракта очень наглядна, но отправить сюда все интерфейсы всех презенторов - будет каша-мала
    }

}