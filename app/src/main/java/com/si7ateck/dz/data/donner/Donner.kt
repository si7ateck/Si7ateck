package com.si7ateck.dz.data.donner

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.si7ateck.dz.data.gps.Location
import com.si7ateck.dz.data.types.BloodType


@Entity(tableName = "donner_table",
    foreignKeys =  [ForeignKey(
    entity = Location::class,
    parentColumns = ["_id_firebase"],
    onDelete = ForeignKey.NO_ACTION,
    childColumns = ["_id_firebase"]
)])
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
    var _blood_type: BloodType,
    @ColumnInfo(name = "_ability")
    var _ability: Boolean
)