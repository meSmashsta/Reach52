package com.reach52.viewmodels

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reach52.entities.User

class UserListViewModel : ViewModel() {

    private val users = MutableLiveData<List<User>>().also {
        it.value = ArrayList()
    }

    fun getUsers(): LiveData<List<User>> {

        if (users.value!!.isEmpty()) {
            loadUsers()
        }

        return users
    }

    private fun loadUsers() {


    }

    fun clearObservers(owner: AppCompatActivity) {
        users.removeObservers(owner)
    }

}