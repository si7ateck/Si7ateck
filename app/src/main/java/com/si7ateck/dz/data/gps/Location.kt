package com.si7ateck.dz.data.gps

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.si7ateck.dz.data.types.City

    @Entity(tableName = "_location_table")
    data class Location(
        @PrimaryKey(autoGenerate = true)
        var _Id: Long,
        @ColumnInfo(name = "_id_firebase")
        var _id_firebase: String,
        @ColumnInfo(name = "_latitude")
        var _latitude: Double,
        @ColumnInfo(name = "_longitude")
        var _longitude: Double,
        @ColumnInfo(name = "_city")
        var _city: City,
        @ColumnInfo(name = "_commune")
        var _commune: String,
        @ColumnInfo(name = "_street")
        var _street: String,
        @ColumnInfo(name = "_distance")
        var _distance: Long =0

    )
