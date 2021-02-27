package com.si7ateck.dz.data.doctor

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.si7ateck.dz.data.gps.Location
import com.si7ateck.dz.data.types.Specialty
import com.si7ateck.dz.data.types.Type
import com.si7ateck.dz.data.workingtime.WorkingTime

@Entity(
    tableName = "doctor_table",
    foreignKeys = [ForeignKey(
        entity = WorkingTime::class,
        parentColumns = [ "_id_firebase"],
        onDelete = ForeignKey.NO_ACTION,
        childColumns =["_id_firebase"]
    ), ForeignKey(
        entity = Location::class,
        parentColumns = [ "_id_firebase"],
        onDelete = ForeignKey.NO_ACTION,
        childColumns =["_id_firebase"]
    )]
)
data class doctor(
    @PrimaryKey(autoGenerate = true)
    var _Id: Long,
    @ColumnInfo(name = "_id_firebase")
    var _id_firebase: String,

    @ColumnInfo(name = "_name")
    val _name: String,

    @ColumnInfo(name = "_speciality")
    var _speciality: Specialty,

    @ColumnInfo(name = "_phone")
    var _phone: String,

    @ColumnInfo(name = "_type")
    var _type: Type
)