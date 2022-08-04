package com.revature.inviewprep.data

import io.reactivex.Single
import java.util.concurrent.TimeUnit

object GreetingRepository{
    fun getHelloGreeting(): Single<String> {
        return Single.just("Hi There!").delay(2, TimeUnit.SECONDS)
    }
}