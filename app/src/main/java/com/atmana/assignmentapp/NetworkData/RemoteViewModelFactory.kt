package com.atmana.assignmentapp.NetworkData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RemoteViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RemoteViewModel::class.java)){
            return RemoteViewModel(repository) as T
        }
        throw IllegalAccessException("Not Available")
    }
}