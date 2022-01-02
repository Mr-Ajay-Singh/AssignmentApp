package com.atmana.assignmentapp.LocalRoomDatabase.RoomData

class UserRepository(val dao : UserDataDAO) {
    val userData = dao.getAllUsersData()

    suspend fun insert(userData: UserData){
       dao.insertData(userData)
    }

    suspend fun update(userData: UserData){
        dao.updateData(userData)
    }

    suspend fun delete(userData: UserData){
        dao.deleteData(userData)
    }
    suspend fun deleteAll(){
        dao.deleteAll()
    }
}