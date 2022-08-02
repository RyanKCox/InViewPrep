package com.revature.inviewprep.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.revature.inviewprep.R
import com.revature.inviewprep.counter.CounterController

class RecyclerAdapter(private val dataSet:List<String>,private val router:Router) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()
{
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val navButton: Button
            init {
                navButton = view.findViewById(R.id.nav_button)
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.button_row_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.navButton.text = dataSet[position]
        holder.navButton.setOnClickListener {
            router.pushController(RouterTransaction.with(CounterController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
        }
    }

    override fun getItemCount() = dataSet.size
}