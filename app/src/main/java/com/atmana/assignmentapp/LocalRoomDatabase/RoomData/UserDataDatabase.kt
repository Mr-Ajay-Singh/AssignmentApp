package com.atmana.assignmentapp.LocalRoomDatabase.RoomData
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserData::class], version = 1)
abstract class UserDataDatabase : RoomDatabase() {
    abstract val userDataDAO: UserDataDAO

    companion object {
        @Volatile
        private var INSTANCE: UserDataDatabase? = null
        fun getInstance(context: Context): UserDataDatabase {
            var instance: UserDataDatabase? = INSTANCE
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataDatabase::class.java,
                    "user_data_table"
                ).build()
            }
            return instance
        }

    }
}