package com.revature.inviewprep.counter

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bluelinelabs.conductor.Controller
import com.revature.inviewprep.MainActivity
import com.revature.inviewprep.R

class CounterController:Controller() {

    private lateinit var viewModel:CounterVM

    private lateinit var counterButton:Button
    private lateinit var counterText:TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.controller_counter,container,false)
        viewModel = ViewModelProvider(container.context as MainActivity)[CounterVM::class.java]

        counterButton = view.findViewById(R.id.counter_button_count)
        counterText = view.findViewById(R.id.counter_textView_count)
        counterText.text = viewModel.counter.toString()

        counterButton.setOnClickListener {
            viewModel.increaseCount()
            counterText.text = viewModel.counter.toString()
        }


        return view
    }
}