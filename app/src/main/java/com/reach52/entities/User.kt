package com.reach52.entities

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
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

    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    @Nullable
    var image: ByteArray? = null

}