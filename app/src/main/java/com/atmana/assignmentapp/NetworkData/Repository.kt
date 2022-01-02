package com.atmana.assignmentapp.NetworkData

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.atmana.assignmentapp.RetrofitFiles.Data
import com.atmana.assignmentapp.RetrofitFiles.DataService
import kotlinx.coroutines.flow.Flow

class Repository(private val dataService: DataService) {
    fun getResult(): Flow<PagingData<Data>>{
        return Pager(
            config = PagingConfig( 8,enablePlaceholders = false, prefetchDistance = 1),
            pagingSourceFactory = {DataListDataSource(dataService)}
        ).flow
    }
}