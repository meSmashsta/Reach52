package com.reach52.repos.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.reach52.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUsers(): List<User>

    @Insert
    fun insertNew(users: User)

}