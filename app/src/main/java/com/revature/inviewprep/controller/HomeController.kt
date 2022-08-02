package com.revature.inviewprep.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluelinelabs.conductor.Controller
import com.revature.inviewprep.R
import com.revature.inviewprep.view.RecyclerAdapter


class HomeController: Controller(){
    private lateinit var rvNav: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.controller_home,container,false)
        rvNav = view.findViewById(R.id.rvNav)

        val data = listOf(
            "Screen 1",
            "Screen 2",
            "Screen 3",
            "Screen 4",
            "Screen 5",
            "Screen 6",
            "Screen 7",
            "Screen 8",
            "Screen 9",
            "Screen 10",
            "Screen 11",
            "Screen 12",
            "Screen 13",
            "Screen 14",
            "Screen 15",
            "Screen 16",
            "Screen 17",
            "Screen 18",
            "Screen 19"
        )
        rvNav.adapter = RecyclerAdapter(data,router)
        rvNav.layoutManager = LinearLayoutManager(view.context)
        return view
    }

}