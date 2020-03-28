package com.reach52.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.reach52.R
import com.reach52.adapters.UserListAdapter
import com.reach52.databinding.ActivityViewBinding
import com.reach52.entities.User
import com.reach52.viewmodels.UserListViewModel

class ViewActivity : AppCompatActivity() {

    private lateinit var b: ActivityViewBinding
    private lateinit var adapter: UserListAdapter
    private lateinit var viewModel: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_view)

        viewModel = ViewModelProvider(this)[UserListViewModel::class.java]
        adapter = UserListAdapter(this)

        b.usersList.adapter = adapter

    }

    override fun onStart() {
        super.onStart()

        viewModel.getUsers().observe(this,
            Observer<List<User>> {
                if (it.isEmpty()) {
                    b.noUserText.visibility = View.VISIBLE
                } else {
                    b.noUserText.visibility = View.GONE
                    adapter.users = it
                }
            }
        )

    }

    override fun onStop() {
        super.onStop()

        viewModel.clearObservers(this)

    }

    fun onAddUserClick(view: View) {
        startActivity(Intent(this, AddActivity::class.java))
    }


}
