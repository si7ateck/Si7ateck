package com.si7ateck.dz.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.si7ateck.dz.data.types.UserType

@Entity(tableName = "user_table")
data class User(
        @PrimaryKey(autoGenerate = true)
        var _Id: Long,
        @ColumnInfo(name = "_id_firebase")
        var _id_firebase: String,
        @ColumnInfo(name = "_name")
        var _name: String,
        @ColumnInfo(name = "_user_type")
        var _user_type: UserType
)