package com.revature.inviewprep.view.home.view

import android.util.Log
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.revature.inviewprep.view.home.model.HomeState
import com.revature.inviewprep.view.home.model.HomeView
import com.revature.inviewprep.view.home.model.NavItem
import com.revature.inviewprep.view.navigation.NavScreens
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class HomePresenter(private val router: Router):MviBasePresenter<HomeView,HomeState>(){


    override fun bindIntents() {

//        val menuItemClicked = intent { it.menuItemIntent() }
////            .switchMap {
////                Log.d("Home","menuItem switchMap")
////                Observable.just(it)
////            }
//            .doOnNext{
//                Log.d("Home","menuItem doOnNext")
//                router.pushController(
//                    RouterTransaction.with(it.controller.newInstance())
//                        .pushChangeHandler(FadeChangeHandler())
//                        .popChangeHandler(FadeChangeHandler()))
//            }
//            .ofType(HomeState::class.java)

        val data = Observable.just(NavScreens.allScreens.drop(1))
            .map { list->
                HomeState.DisplayScreens(list)
            }
            .ofType(HomeState::class.java)

        val viewState = data

        subscribeViewState(viewState){ view,state->view.render(state) }
    }

}