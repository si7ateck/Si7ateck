package com.si7ateck.dz.data.pharmacy

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pharmacy_table")
data class Pharmacy(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var _id: Long,

    @ColumnInfo(name = "_id_firebase")
    var _id_firebase: String,

    @ColumnInfo(name = "_name")
    var name: String,

    @ColumnInfo(name = "_mobile_number")
    var mobile: String,

    @ColumnInfo(name = "_description")
    var description: String,

    @ColumnInfo(name = "_address")
    var address: String,

    @ColumnInfo(name = "_id_working_time")
    var _id_working_time: String



)