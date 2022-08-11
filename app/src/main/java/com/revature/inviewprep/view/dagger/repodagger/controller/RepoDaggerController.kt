package com.revature.inviewprep.view.dagger.repodagger.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.hannesdorfmann.mosby3.MviController
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.revature.inviewprep.R
import com.revature.inviewprep.databinding.ControllerRepodaggerBinding
import com.revature.inviewprep.view.dagger.repodagger.di.DaggerRepoDaggerComponent
import com.revature.inviewprep.view.dagger.repodagger.di.RepoDaggerModule
import com.revature.inviewprep.view.dagger.repodagger.presenter.RepoDaggerPresenter
import timber.log.Timber
import javax.inject.Inject

class RepoDaggerController :MviController<RepoDaggerView,RepoDaggerPresenter>(),RepoDaggerView{

    lateinit var textview:TextView
    lateinit var progress:ProgressBar

    @Inject
    lateinit var presenter: RepoDaggerPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.controller_repodagger,container,false)
        setup(view)
        return view
    }
    private fun setup(view:View){
        val binder = ControllerRepodaggerBinding.bind(view)
        textview = binder.textRepodagger
        progress = binder.progressRepodagger

        val repoDaggerComponent = DaggerRepoDaggerComponent.builder()
            .repoDaggerModule( RepoDaggerModule("An Example"))
            .build()
        repoDaggerComponent.inject(this)
        Timber.d("userRepository.getName = ${presenter.userRepository.getName()}")

    }

    override fun createPresenter() = presenter//RepoDaggerPresenter(UserDataRepository("asda"))

    override fun render(viewState: RepoDaggerVS) {
        when(viewState){
            is RepoDaggerVS.Display->{
                textview.text = viewState.text
                progress.visibility = View.GONE
                textview.visibility = View.VISIBLE
            }
            is RepoDaggerVS.Loading->{
                textview.visibility = View.GONE
                progress.visibility = View.VISIBLE

            }
        }
    }

}

interface RepoDaggerView:MvpView {
    fun render(viewState:RepoDaggerVS)
}
sealed class RepoDaggerVS{
    data class Display(val text:String):RepoDaggerVS()
    object Loading:RepoDaggerVS()
}