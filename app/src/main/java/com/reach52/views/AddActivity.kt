package com.reach52.views

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.reach52.R
import com.reach52.databinding.ActivityAddBinding
import com.reach52.viewmodels.AddUserViewModel
import java.io.File
import java.util.*


class AddActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    DialogInterface.OnClickListener {

    private val REQUEST_TAKE_PICTURE = 100
    private val REQUEST_CHOOSE_PICTURE = 101

    private lateinit var b: ActivityAddBinding
    private lateinit var viewModel: AddUserViewModel
    private lateinit var imageSelectionPopup: AlertDialog
    private lateinit var imageUri: Uri

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

        val builder = AlertDialog.Builder(this)
        builder.setItems(
            arrayOf(
                "Capture photo",
                "Choose from gallery"
            ), this
        )
        imageSelectionPopup = builder.create()


    }

    fun onSaveClick(view: View) {

        viewModel.enteredName = b.addName.text.toString().trim()
        viewModel.enteredAddress = b.addAddress.text.toString().trim()
        viewModel.capturedImageUri = imageUri.toString()
        viewModel.addNewUser {
            if (it == null) {
                finish()
            } else {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }

    }

    fun onAddImageSelect(view: View) {

        imageSelectionPopup.show()

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        viewModel.enteredDoB = Date(year, month, dayOfMonth).time
        setDateText(year, month, dayOfMonth)

    }

    private fun setDateText(year: Int, month: Int, day: Int) {
        b.addDob.setText("$day/$month/$year")
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {

        when (which) {
            0 -> {// capture from camera
                launchCamera()
            }
            1 -> {// select from gallery
                launchGallery()
            }

        }
        dialog?.dismiss()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {

            REQUEST_TAKE_PICTURE -> {

                if (resultCode == Activity.RESULT_OK) {
                    Glide.with(this).load(imageUri).into(b.selectedImage)
                }

            }

            REQUEST_CHOOSE_PICTURE -> {

                if (resultCode == Activity.RESULT_OK && data != null) {
                    imageUri = data.data
                    Glide.with(this).load(imageUri).into(b.selectedImage)
                }

            }

        }

    }

    private fun launchCamera() {

        val file = File(filesDir, "${Date().time}.jpeg")
        imageUri = FileProvider.getUriForFile(this, "$packageName.provider", file)


        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(cameraIntent, REQUEST_TAKE_PICTURE)
    }

    private fun launchGallery() {

        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        intent.type = "image/*"
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        startActivityForResult(intent, REQUEST_CHOOSE_PICTURE)

    }

}
