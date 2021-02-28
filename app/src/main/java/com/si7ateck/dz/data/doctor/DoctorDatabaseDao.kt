package com.si7ateck.dz.data.doctor

import androidx.lifecycle.LiveData
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
}