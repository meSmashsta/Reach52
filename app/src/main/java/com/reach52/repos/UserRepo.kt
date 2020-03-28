package com.reach52.repos

import android.os.AsyncTask
import com.reach52.entities.User
import com.reach52.repos.local.AppDatabase

object UserRepo {

    private val db = AppDatabase.getDatabase()

    fun getUsers(fetchCallback: (List<User>?) -> Unit = {}) {

        object : AsyncTask<Void, Void, List<User>>() {
            override fun doInBackground(vararg params: Void?): List<User> {
                return db.userDao().getUsers()
            }

            override fun onPostExecute(result: List<User>?) {
                super.onPostExecute(result)
                fetchCallback(result)
            }

        }.execute()

    }

    fun insertUser(user: User, insertCallback: () -> Unit = {}) {

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {

                db.userDao().insertNew(user)

                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                insertCallback()
            }

        }.execute()

    }


}