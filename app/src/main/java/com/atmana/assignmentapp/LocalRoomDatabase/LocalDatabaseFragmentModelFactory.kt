package com.practice.roomdatabasepractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.atmana.assignmentapp.LocalRoomDatabase.RoomData.UserRepository

class LocalDatabaseFragmentModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LocalDatabaseFragmentModel::class.java)){
            return LocalDatabaseFragmentModel(repository) as T
        }
        throw IllegalAccessException("Not Available")
    }
}