package com.revature.inviewprep.view.home.controller

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.mosby3.MviController
import com.revature.inviewprep.R
import com.revature.inviewprep.view.home.model.HomeState
import com.revature.inviewprep.view.home.model.HomeView
import com.revature.inviewprep.view.home.model.NavItem
import com.revature.inviewprep.view.home.view.HomePresenter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder


class HomeController: MviController<HomeView,HomePresenter>(),HomeView{
    private lateinit var rvNav: RecyclerView
    private val adapter:GroupAdapter<GroupieViewHolder> = GroupAdapter()
//    private val menuListSubject:PublishSubject<NavScreens<out Controller>>
//        = PublishSubject.create()

    init {
        Log.d("Home","Init Hit")
//        adapter.setOnItemClickListener { item, _ ->
//
//            Log.d("Home", "adapter click listener created")
//            menuListSubject.onNext((item as NavItem).nav)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup
    ): View {
        val view = inflater.inflate(R.layout.controller_home,container,false)
        setupUI(view)

        return view
    }

    private fun setupUI(view:View){

        rvNav = view.findViewById(R.id.rvNav)
        rvNav.layoutManager = LinearLayoutManager(view.context)

        rvNav.adapter = adapter

    }

    override fun createPresenter() = HomePresenter(/*router*/)

//    override fun menuItemIntent(): Observable<NavScreens<out Controller>> {
//        Log.d("Home","menuItemIntent - Override Hit")
//        return menuListSubject.hide()
//    }

    override fun render(viewState: HomeState) {
        when(viewState){
            is HomeState.DisplayScreens ->{
                adapter.clear()
                adapter.addAll(viewState.data.map { NavItem(it,router) })
                rvNav.visibility = View.VISIBLE
            }
            is HomeState.Loading ->{
                rvNav.visibility = View.GONE
            }
        }
    }


}