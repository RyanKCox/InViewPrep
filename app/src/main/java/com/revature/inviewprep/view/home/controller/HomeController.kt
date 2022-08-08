package com.revature.inviewprep.view.home.controller

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.hannesdorfmann.mosby3.MviController
import com.revature.inviewprep.R
import com.revature.inviewprep.view.home.model.HomeState
import com.revature.inviewprep.view.home.model.HomeView
import com.revature.inviewprep.view.home.model.NavItem
import com.revature.inviewprep.view.home.view.HomePresenter
import com.revature.inviewprep.view.navigation.NavScreens
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.OnItemClickListener
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class HomeController: MviController<HomeView,HomePresenter>(),HomeView{
    private lateinit var rvNav: RecyclerView
    private val adapter:GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private val menuListSubject:PublishSubject<NavScreens<out Controller>>
        = PublishSubject.create()

    init {
        adapter.setOnItemClickListener { item, _ -> menuListSubject.onNext((item as NavItem).nav)  }
        Log.d("Home","adapter click listener created")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
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

    override fun createPresenter() = HomePresenter(router)

    override fun menuItemIntent(): Observable<NavScreens<out Controller>> {
        return menuListSubject.hide()
    }

    override fun render(viewState: HomeState) {
        when(viewState){
            is HomeState.DisplayScreens ->{
                adapter.clear()
                adapter.addAll(viewState.data)
                rvNav.visibility = View.VISIBLE
            }
            is HomeState.Loading ->{
                rvNav.visibility = View.GONE
            }
        }
    }


}