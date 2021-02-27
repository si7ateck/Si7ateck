package com.si7ateck.dz.data.doctor

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DoctorDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(doctor: doctor)

    @Update
    suspend fun update(doctor: doctor)

    @Query("DELETE FROM doctor_table")
    suspend fun clear()

    @Query("SELECT * FROM doctor_table ORDER BY _Id DESC" )
    fun getAllDoctors(): LiveData<List<doctor>>
}