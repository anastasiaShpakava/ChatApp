package com.company.chatapp.repository

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.company.chatapp.MainActivity
import com.company.chatapp.R
import com.company.chatapp.ui.SecondFragment
import com.google.android.gms.common.internal.service.Common
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseRepository{

     private var auth: FirebaseAuth = FirebaseAuth.getInstance()
  var mutableLiveData: MutableLiveData<FirebaseUser> = MutableLiveData()
    private var application:Application = Application()


    fun register(password: String, email: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                 mutableLiveData.postValue(auth.currentUser)
//                val userId = auth.currentUser!!.uid //TODO
//                reference = FirebaseDatabase.getInstance().getReference("Users").child(userId)
//                val map = mapOf("id" to userId, "username" to auth.currentUser!!.displayName, "imageURL" to "default")
            } else {
                Toast.makeText((application), "Registration failed", Toast.LENGTH_LONG).show()
            }
        }
    }
}