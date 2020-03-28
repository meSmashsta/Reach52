package com.reach52.viewmodels

import androidx.lifecycle.ViewModel
import com.reach52.entities.User
import com.reach52.repos.UserRepo
import com.reach52.utils.logi

class AddUserViewModel : ViewModel() {

    var capturedImageUri: String? = null
    var enteredDoB: Long = -1
    var enteredName: String = ""
    var enteredAddress: String? = null

    fun addNewUser(callback: (String?) -> Unit) {

        val user = User(enteredName, enteredDoB)

        if (enteredName.isEmpty()) {
            callback("Name cannot be empty")
            return
        }

        if (enteredDoB <= 0L) {
            callback("Date of Birth not set")
            return
        }

        if (enteredAddress != null) {
            user.address = enteredAddress
        }

        if (capturedImageUri != null) {
            user.imageUri = capturedImageUri
        }

        logi("adding new user:\n$user")

        UserRepo.insertUser(user) {
            callback(null)
        }

    }

}