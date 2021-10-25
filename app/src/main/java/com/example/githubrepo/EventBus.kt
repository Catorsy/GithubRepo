package com.example.githubrepo

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class EventBus {
    data class EventAddMessage(val message: String?) {
    }

    private val bus = PublishSubject.create<Any>()

    fun post(event: Any) {
        bus.onNext(event)
    }

    //fun get(): Observable<Event> = bus

    fun <T> listen(eventType: Class<T>): Observable<T> = bus.ofType(eventType)
}