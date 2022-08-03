package com.revature.inviewprep.home.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluelinelabs.conductor.Controller
import com.revature.inviewprep.R
import com.revature.inviewprep.counter.CounterVM
import com.revature.inviewprep.navigation.NavScreens
import com.revature.inviewprep.home.view.RecyclerAdapter


class HomeController: Controller(){
    private lateinit var rvNav: RecyclerView
    private lateinit var counterVM:CounterVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.controller_home,container,false)
        setupUI(view)
        setupViewModel()

        return view
    }

    private fun setupViewModel() {

    }

    private fun setupUI(view:View){rvNav = view.findViewById(R.id.rvNav)

        val data = NavScreens.allScreens.drop(1)
        rvNav.adapter = RecyclerAdapter(data,router)
        rvNav.layoutManager = LinearLayoutManager(view.context)

    }


}