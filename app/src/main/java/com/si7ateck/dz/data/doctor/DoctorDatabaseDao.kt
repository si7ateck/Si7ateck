package com.si7ateck.dz.data.doctor

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface DoctorDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(doctor: Doctor)

    @Update
    suspend fun update(doctor: Doctor)

    @Query("DELETE FROM doctor_table")
    suspend fun clear()

    @Query("SELECT * FROM doctor_table ORDER BY _Id DESC" )
    fun getAllDoctors(): LiveData<List<Doctor>>

    @Query("SELECT * FROM doctor_table WHERE _name LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<Doctor>>


    @Query("SELECT _city || _street   FROM doctor_table D ,_location_table L WHERE D._id_firebase=L._id_firebase and D._id_firebase LIKE :address ")
    fun getAddress(address:String): String

    @Query("SELECT _city || _street   FROM doctor_table D ,_location_table L WHERE D._id_firebase=L._id_firebase")
    fun getAllAddress(): LiveData<List<String>>
}