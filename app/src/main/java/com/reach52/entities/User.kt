package com.reach52.entities

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
class User(name: String, dob: Long) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "name")
    @NonNull
    val name = name

    @ColumnInfo(name = "dob")
    @NonNull
    val dob: Long = dob

    @ColumnInfo(name = "address")
    @Nullable
    var address: String? = null

    @ColumnInfo(name = "imageUri")
    @Nullable
    var imageUri: String? = null

    override fun toString(): String {
        return "User(id=$id, name='$name', dob=$dob, address=$address, imageUri=$imageUri)"
    }


}