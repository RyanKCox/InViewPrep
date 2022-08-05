package com.revature.inviewprep.view.mosby.mosby_hello

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.revature.inviewprep.domain.GetGreetingUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class GreetingsPresenter :
    MviBasePresenter<GreetingsContract.View, GreetingsViewState>(){


    override fun bindIntents() {

        val start = Observable.just(GreetingsViewState.StartDisplay as GreetingsViewState)

        val buttonClickObs: Observable<GreetingsViewState> =
            intent { it.displayIntent() }
                .switchMap { greeting->

//                    val test = GetGreetingUseCase.getHelloGreeting()
                    Observable.just(GreetingsViewState.Display(greeting) as GreetingsViewState)
                        .delay(2,TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .startWith(GreetingsViewState.Loading)
                        .subscribeOn(AndroidSchedulers.mainThread())}

        val viewState = start.mergeWith(buttonClickObs)

        subscribeViewState(viewState){view,state -> view.render(state)}

    }

}
sealed class GreetingsViewState{
    object Loading:GreetingsViewState()
    object StartDisplay:GreetingsViewState()
    data class Display(
        val greeting:String
        ):GreetingsViewState()
}