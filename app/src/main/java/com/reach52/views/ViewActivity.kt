package com.reach52.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
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
        setSupportActionBar(b.toolbar)

        viewModel = ViewModelProvider(this)[UserListViewModel::class.java]
        adapter = UserListAdapter(this)

        b.usersList.adapter = adapter
        viewModel.users.observe(
            this,
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

    override fun onStart() {
        super.onStart()

        viewModel.loadUsers()

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearObservers(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.sort_options) {

            val view = findViewById<View>(R.id.sort_options)

            val popupMenu = PopupMenu(this, view)
            popupMenu.menuInflater.inflate(R.menu.view_menu_popup, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem?): Boolean {

                    when (item?.itemId) {
                        R.id.sort_by_age -> {
                            viewModel.loadUsers(true)
                        }
                        R.id.sort_by_name -> {
                            viewModel.loadUsers(false)
                        }
                    }

                    return true
                }

            })
            popupMenu.show()

        }

        return true
    }

    fun onAddUserClick(view: View) {
        startActivity(Intent(this, AddActivity::class.java))
    }


}
