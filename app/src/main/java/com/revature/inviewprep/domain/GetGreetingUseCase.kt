package com.revature.inviewprep.domain

import com.revature.inviewprep.data.GreetingRepository
import io.reactivex.Single

object GetGreetingUseCase {
    fun getHelloGreeting():Single<String> = GreetingRepository.getHelloGreeting()
}