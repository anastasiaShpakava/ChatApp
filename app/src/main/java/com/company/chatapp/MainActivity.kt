package com.company.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.company.chatapp.ui.RegisterFragment

class MainActivity : AppCompatActivity() {
    private var registerFragment: RegisterFragment? = RegisterFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //keep screen active
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        if (savedInstanceState == null) {
            registerFragment = RegisterFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.frameLayout, registerFragment!!, "registerFragment")
                .commit()
        } else {
            registerFragment = supportFragmentManager
                .findFragmentByTag("registerFragment") as? RegisterFragment
        }
    }
}