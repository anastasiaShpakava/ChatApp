package com.company.chatapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.company.chatapp.R


class MainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_main, container, false)

        var btnRegister = view.findViewById(R.id.button_register) as Button
        var btnLogin = view.findViewById(R.id.button_login) as Button

        btnRegister.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.registerFragment)
        }

        btnLogin.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.loginFragment)
        }
        return view
    }

}