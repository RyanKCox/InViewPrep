package com.revature.inviewprep.view.dagger.hellodagger.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.hannesdorfmann.mosby3.MviController
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.revature.inviewprep.R
import com.revature.inviewprep.databinding.ControllerDaggerExampleBinding
import com.revature.inviewprep.view.dagger.hellodagger.presenter.DaggerPresenter

class DaggerController :MviController<DaggerView, DaggerPresenter>(), DaggerView {

    lateinit var textView:TextView
    lateinit var progress:ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.controller_dagger_example,container,false)
        setup(view)
        return view
    }
    private fun setup(view:View){

        val binder = ControllerDaggerExampleBinding.bind(view)
        textView = binder.textDagger
        progress = binder.progressDagger

    }

    override fun createPresenter() = DaggerPresenter()

    override fun render(viewState: DaggerViewState) {
        when(viewState){
            is DaggerViewState.DisplayScreen ->{
                textView.text = viewState.text
                progress.visibility = View.GONE
                textView.visibility = View.VISIBLE

            }
            is DaggerViewState.Loading ->{
                progress.visibility = View.VISIBLE
                textView.visibility = View.GONE

            }
        }
    }

}
interface DaggerView:MvpView{
    fun render(viewState: DaggerViewState)
}
sealed class DaggerViewState{
    data class DisplayScreen(val text:String): DaggerViewState()
    object Loading: DaggerViewState()

}