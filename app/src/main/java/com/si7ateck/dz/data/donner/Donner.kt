package com.si7ateck.dz.data.donner

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.si7ateck.dz.data.types.BloodType


@Entity(tableName = "donner_table")
data class Donner(
    @PrimaryKey(autoGenerate = true)
    var _Id: Long,
    @ColumnInfo(name = "_id_firebase")
    var _id_firebase: String,
    @ColumnInfo(name = "_name")
    var _name: String,
    @ColumnInfo(name = "_phone")
    var _phone: String,
    @ColumnInfo(name = "_blood_type")
    var _bloodtype: BloodType
)