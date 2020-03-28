package com.reach52.viewmodels

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reach52.entities.User
import com.reach52.repos.UserRepo

class UserListViewModel : ViewModel() {

    val users = MutableLiveData<List<User>>().also {
        it.value = ArrayList()
    }

    fun loadUsers() {

        UserRepo.getUsers {
            users.value = it
        }

    }

    fun clearObservers(owner: AppCompatActivity) {
        users.removeObservers(owner)
    }

}