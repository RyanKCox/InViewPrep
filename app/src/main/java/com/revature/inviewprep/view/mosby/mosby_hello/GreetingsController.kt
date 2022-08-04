package com.revature.inviewprep.view.mosby.mosby_hello

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.bluelinelabs.conductor.Controller
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.revature.inviewprep.R

class GreetingsController :
    MvpController<GreetingsContract.View,GreetingsContract.Presenter>(),
        GreetingsContract.View
{

    lateinit var greetingsTV:TextView
    lateinit var loadingIndicator:ProgressBar
    lateinit var greetingButton:Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.controller_mosby_hello,container,false)
        greetingsTV = view.findViewById(R.id.greeting_textview)
        loadingIndicator = view.findViewById(R.id.loading_indicator)
        greetingButton =view.findViewById(R.id.hello_greeting_button)

        greetingButton.setOnClickListener {
            onGreetingButtonClicked()
        }

        return view
    }

    override fun createPresenter(): GreetingsContract.Presenter = GreetingsPresenter()

    override fun onGreetingButtonClicked() {
        presenter.loadGreeting()
    }

    override fun showLoading() {
        greetingsTV.visibility = View.GONE
        loadingIndicator.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingIndicator.visibility = View.GONE
    }

    override fun showGreeting(greetingText: String) {
        greetingsTV.visibility = View.VISIBLE
        greetingsTV.text = greetingText
    }

    override fun showError() {
        Toast.makeText(
            applicationContext,
            applicationContext?.getString(R.string.greeting_loading_error),
            Toast.LENGTH_LONG).show()
    }


}