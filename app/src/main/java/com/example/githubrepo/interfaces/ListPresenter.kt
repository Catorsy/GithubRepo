package com.example.githubrepo.interfaces

//здесь общие для всех списков функции
interface ListPresenter<V : ItemView> {
    //unit ничего не возвращает, он как синглтон
    var itemClickListener: ((V) -> Unit)? //при обработке клика получаем от Вью позицию и находим требуемый элемент

    //функция нполнения вью
    fun bindView(view: V)
    fun getCount(): Int
}