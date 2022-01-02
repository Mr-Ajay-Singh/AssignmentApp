package com.atmana.assignmentapp.LocalRoomDatabase.RoomData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data_table")
data class UserData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="user_id")
    var id : Int,
    @ColumnInfo(name = "user_first_name")
    var firstName : String,
    @ColumnInfo(name = "user_last_name")
    var lastName : String,
    @ColumnInfo(name = "user_email")
    var userEmail : String
)