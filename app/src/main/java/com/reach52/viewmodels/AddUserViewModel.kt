package com.reach52.viewmodels

import androidx.lifecycle.ViewModel
import com.reach52.entities.User
import com.reach52.repos.UserRepo

class AddUserViewModel : ViewModel() {

    fun addNewUser(user: User, callback: () -> Unit) {

        UserRepo.insertUser(user, callback)

    }

}