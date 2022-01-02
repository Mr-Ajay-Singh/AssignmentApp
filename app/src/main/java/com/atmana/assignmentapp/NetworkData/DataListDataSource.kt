package com.atmana.assignmentapp.NetworkData

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.atmana.assignmentapp.RetrofitFiles.Data
import com.atmana.assignmentapp.RetrofitFiles.DataService

class DataListDataSource(private val dataService : DataService) : PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val page = params.key ?: 1
        return try{
            val response = dataService.getData(page).data

            Log.i("MyTAG",page.toString()+" "+"Page Number "+ response.size.toString())
            LoadResult.Page(
                response,
                prevKey = if(page == 1) null else page-1,
                nextKey = if(response.size==0) null else page+1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }

    }

}