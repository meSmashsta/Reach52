package com.reach52.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.reach52.R
import com.reach52.databinding.ActivityAddBinding
import com.reach52.viewmodels.AddUserViewModel

class AddActivity : AppCompatActivity() {

    lateinit var b: ActivityAddBinding
    lateinit var viewModel: AddUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_add)
        viewModel = ViewModelProvider(this)[AddUserViewModel::class.java]

    }

    fun onSaveClick(view: View) {

    }

}
