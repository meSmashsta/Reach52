package com.reach52.repos.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.reach52.MyApp
import com.reach52.entities.User

@Database(entities = [User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var db: AppDatabase? = null

        fun getDatabase(): AppDatabase {
            val tempInstance = db
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    MyApp.get(),
                    AppDatabase::class.java,
                    "local_user_db"
                ).fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }
        }
    }

}