package com.si7ateck.dz.data.workingtime

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek

@Entity(tableName = "_working_time_table")
data class WorkingTime(
    @PrimaryKey(autoGenerate = true)
    var _Id: Long,
    @ColumnInfo(name = "_id_firebase")
    var _id_firebase: String,
    @ColumnInfo(name = "_sunday")
    var _sunday: String,
    @ColumnInfo(name = "_monday")
    var _monday: String,
    @ColumnInfo(name = "_tuesday")
    var _tuesday: String,
    @ColumnInfo(name = "_wednesday")
    var _wednesday: String,
    @ColumnInfo(name = "_thursday")
    var _thursday: String,
    @ColumnInfo(name = "_friday")
    var _friday: String,
    @ColumnInfo(name = "_saturday")
    var _saturday: String
)