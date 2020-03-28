package com.reach52.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reach52.R
import com.reach52.databinding.XUserItemBinding
import com.reach52.entities.User
import com.reach52.utils.calculateAge
import com.reach52.utils.stringFromLongDate

class UserListAdapter(val context: Context) :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    var users: List<User>? = null
        set(users) {
            field = users
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val binding = DataBindingUtil.inflate<XUserItemBinding>(
            LayoutInflater.from(context),
            R.layout.x_user_item,
            parent,
            false
        )

        return UserViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return if (users == null)
            0
        else
            users!!.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        users?.let {

            val user = it[position]

            holder.setUser(user)

        }


    }

    inner class UserViewHolder(var b: XUserItemBinding) : RecyclerView.ViewHolder(b.root) {

        fun setUser(user: User) {

            b.user = user
            b.userDob.text = "DoB: ${stringFromLongDate(user.dob)}"
            b.userAge.text = "Age: ${calculateAge(user.dob)} Years"
            Glide.with(context).load(user.imageUri).into(b.userImage)

        }

    }

}