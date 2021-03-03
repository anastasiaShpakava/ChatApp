package com.company.chatapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class SplashViewModel:ViewModel()  {


    private val _splashCommand = SingleLiveEvent<Boolean>()
    private val _progress = MutableLiveData<Int>()

    val splashCommand: LiveData<Boolean> = _splashCommand
    val progress: LiveData<Int> = _progress

    init {
        viewModelScope.launch {

            delay(DELAY_BEFORE_START_LOADING)
            repeat(4){
                delay(STEP_OF_LOADING)
                _progress.postValue(25 * (it + 1))
            }
            _splashCommand.postValue(true)
        }
    }

    private companion object{
        const val DELAY_BEFORE_START_LOADING = 500L
        const val STEP_OF_LOADING = 500L
    }


}