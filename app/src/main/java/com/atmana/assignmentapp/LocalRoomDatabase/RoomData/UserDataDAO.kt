package com.atmana.assignmentapp.LocalRoomDatabase.RoomData
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDataDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(userData: UserData)

    @Insert
    suspend fun insertData(userData: UserData, subsList : List<UserData>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateData(userData: UserData)

    @Delete
    suspend fun deleteData(subscriber: UserData)

    @Query(value = "DELETE  FROM user_data_table")
    suspend fun deleteAll()

    //as live data do already in background thats why we dont need suspend modifier
    @Query(value = "SELECT *  FROM user_data_table")
    fun getAllUsersData() : LiveData<List<UserData>>

}