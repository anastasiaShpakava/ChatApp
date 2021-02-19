package com.company.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.Navigation


class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //keep screen active
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }
}