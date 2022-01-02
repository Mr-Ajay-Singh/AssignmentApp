package com.practice.roomdatabasepractice

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atmana.assignmentapp.LocalRoomDatabase.RoomData.UserData
import com.atmana.assignmentapp.LocalRoomDatabase.RoomData.UserRepository
import kotlinx.coroutines.launch

class DialogFragmentModel(
    private val repository: UserRepository
) : ViewModel(), Observable {

    val userData = repository.userData
//    var updateAndDelete = false
//
//    @Bindable
//    val inputFirstName = MutableLiveData<String?>()
//
//    @Bindable
//    val inputLastName = MutableLiveData<String?>()
//
//    @Bindable
//    val inputEmail = MutableLiveData<String?>()
//
//    @Bindable
//    val saveOrUpdateButtonText = MutableLiveData<String>()
//
//    @Bindable
//    val clearAllOrDeleteButtonText = MutableLiveData<String>()
//
//    init {
//        saveOrUpdateButtonText.value = "Save"
//        clearAllOrDeleteButtonText.value = "Clear All"
//    }
//
//    fun saveOrUpdate() {
//        if (updateAndDelete) {
//            update(subscriberToUpdateAndDelete)
//            events.value = Event("Data Updated")
//        } else {
//            val name: String = inputName.value!!
//            val email: String = inputEmail.value!!
//            val resultOutput = insert(Subscriber(0, name, email))
//            inputName.value = null
//            inputEmail.value = null
//            events.value = Event("Data Inserted +${resultOutput}")
//        }
//    }
//
//    fun deleteData(){
//        if(updateAndDelete) {
//            inputName.value = null
//            inputEmail.value = null
//            updateAndDelete = !updateAndDelete
//            saveOrUpdateButtonText.value = "Save"
//            clearAllOrDeleteButtonText.value = "ClearAll"
//            delete(subscriberToUpdateAndDelete)
//        }else{
//            deleteAll()
//        }
//        events.value = Event("Data Deleted")
//    }
//
//    fun initUpdateAndDelete(subscriber: Subscriber) {
//
//        inputName.value = subscriber.name
//        inputEmail.value = subscriber.email
//        subscriberToUpdateAndDelete = subscriber
//        saveOrUpdateButtonText.value = "Update"
//        clearAllOrDeleteButtonText.value = "Delete"
//
//    }
//
    fun insert(userData: UserData) {
        viewModelScope.launch {
             val res = repository.insert(userData)
        }
    }

    fun update(userData: UserData) {
        viewModelScope.launch {
            repository.update(userData)
        }
    }
//
//    fun delete(subscriber: Subscriber) {
//        viewModelScope.launch {
//            repository.delete(subscriber)
//        }
//    }
//
//    fun deleteAll() {
//        viewModelScope.launch {
//            repository.deleteAll()
//        }
//    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }


}