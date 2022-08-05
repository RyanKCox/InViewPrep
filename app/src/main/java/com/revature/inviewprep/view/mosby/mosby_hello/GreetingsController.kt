package com.revature.inviewprep.view.mosby.mosby_hello

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.hannesdorfmann.mosby3.MviController
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.view.visibility
import com.revature.inviewprep.R
import io.reactivex.Observable

class GreetingsController :
    MviController<GreetingsContract.View, GreetingsPresenter>(),
        GreetingsContract.View
{

    lateinit var greetingsTV:TextView
    lateinit var loadingIndicator:ProgressBar
    lateinit var greetingButton:View


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.controller_mosby_hello,container,false)
        greetingsTV = view.findViewById(R.id.greeting_textview)
        loadingIndicator = view.findViewById(R.id.loading_indicator)
        greetingButton =view.findViewById(R.id.hello_greeting_button)

        return view
    }

    override fun createPresenter(): GreetingsPresenter = GreetingsPresenter()


    override fun displayIntent():Observable<String>{
        return greetingButton.clicks()
            .map { click -> "HelloWorld" }
    }

    override fun render(state: GreetingsViewState) {
        when(state){
            is GreetingsViewState.Display ->{
                loadingIndicator.visibility = View.GONE
                greetingsTV.text = state.greeting
                greetingsTV.visibility = View.VISIBLE
            }
            is GreetingsViewState.Loading -> {
                loadingIndicator.visibility = View.VISIBLE
                greetingsTV.visibility = View.GONE
            }
            is GreetingsViewState.StartDisplay -> {
                loadingIndicator.visibility = View.GONE
                greetingsTV.visibility = View.GONE
            }
        }
    }
}