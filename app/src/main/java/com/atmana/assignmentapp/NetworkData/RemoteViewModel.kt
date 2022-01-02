package com.atmana.assignmentapp.NetworkData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.atmana.assignmentapp.RetrofitFiles.Data
import kotlinx.coroutines.flow.Flow

class RemoteViewModel(private val repository: Repository) : ViewModel() {

     val result: Flow<PagingData<Data>> = repository.getResult().cachedIn(viewModelScope)


     fun getAllData() : Flow<PagingData<Data>>{
          return result
     }
}