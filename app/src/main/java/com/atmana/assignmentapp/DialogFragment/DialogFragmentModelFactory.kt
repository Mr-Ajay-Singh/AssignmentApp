package com.practice.roomdatabasepractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.atmana.assignmentapp.LocalRoomDatabase.RoomData.UserRepository

class DialogFragmentModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DialogFragmentModel::class.java)){
            return DialogFragmentModel(repository) as T
        }
        throw IllegalAccessException("Not Available")
    }
}