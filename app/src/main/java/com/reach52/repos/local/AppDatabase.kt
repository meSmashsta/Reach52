package com.reach52.repos.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.reach52.MyApp
import com.reach52.entities.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    val db = Room.databaseBuilder(
        MyApp.get(),
        AppDatabase::class.java, "local_user_db"
    ).build()

}