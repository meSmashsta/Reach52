package com.reach52.views

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.reach52.R
import com.reach52.databinding.ActivityAddBinding
import com.reach52.utils.logd
import com.reach52.viewmodels.AddUserViewModel
import java.util.*


class AddActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var b: ActivityAddBinding
    private lateinit var viewModel: AddUserViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_add)
        viewModel = ViewModelProvider(this)[AddUserViewModel::class.java]

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(this, this, year, month, day)

        b.addDob.setOnClickListener {
            datePicker.show()
        }

    }

    fun onSaveClick(view: View) {

        viewModel.enteredName = b.addName.text.toString().trim()
        viewModel.enteredAddress = b.addAddress.text.toString().trim()
        viewModel.addNewUser {
            if (it == null) {
                finish()
            } else {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        viewModel.enteredDoB = Date(year, month, dayOfMonth).time
        logd("entered DoB: ${viewModel.enteredDoB}")
        setDateText(year, month, dayOfMonth)

    }

    private fun setDateText(year: Int, month: Int, day: Int) {
        b.addDob.setText("$day/$month/$year")
    }

}
