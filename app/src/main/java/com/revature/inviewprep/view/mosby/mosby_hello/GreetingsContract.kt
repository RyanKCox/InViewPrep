package com.revature.inviewprep.view.mosby.mosby_hello

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface GreetingsContract{
    interface View:MvpView{
        fun displayIntent():Observable<String>
        fun render(state: GreetingsViewState)
    }
}