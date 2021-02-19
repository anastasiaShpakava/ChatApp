package com.company.chatapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.company.chatapp.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseUser

class LoginRegisterViewModel(application: Application) : AndroidViewModel(application) {
    private var firebaseRepository:FirebaseRepository = FirebaseRepository()
  var mutableLiveData: MutableLiveData<FirebaseUser>
    init {
        mutableLiveData = firebaseRepository.mutableLiveData
    }
     fun register(password: String, email: String){
        firebaseRepository.register(password,email)
    }
}