package com.revature.inviewprep.view.mosby.mosby_hello

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.revature.inviewprep.domain.GetGreetingUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GreetingsPresenter :
    MvpBasePresenter<GreetingsContract.View>(), GreetingsContract.Presenter{

    private val disposable:CompositeDisposable = CompositeDisposable()

    override fun loadGreeting() {
        disposable.add(GetGreetingUseCase.getHelloGreeting()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{ ifViewAttached { view->view.showLoading() }}
            .doFinally{ ifViewAttached { view->view.hideLoading() }}
            .subscribe({ifViewAttached { view->view.showGreeting(it) }},{ifViewAttached { view->view.showError() }}))
    }

    override fun destroy() {
        super.destroy()
        disposable.clear()
    }

}