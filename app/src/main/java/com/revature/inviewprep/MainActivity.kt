package com.revature.inviewprep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.revature.inviewprep.home.controller.HomeController

class MainActivity : AppCompatActivity() {

    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val container = findViewById<ViewGroup>(R.id.controller_container)
        router = Conductor.attachRouter(this,container,savedInstanceState)
        if(!router.hasRootController())
            router.setRoot(RouterTransaction.with(HomeController()))
    }

    override fun onBackPressed() {
        if(!router.handleBack())
            super.onBackPressed()
    }
}
