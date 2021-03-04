package com.company.chatapp.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.company.chatapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


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

    fun login(password: String, email: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                mutableLiveData.postValue(auth.currentUser)
            }else{
                Toast.makeText((application), "Authentication is failed", Toast.LENGTH_LONG).show()
            }
        }
    }
}