package com.si7ateck.dz.data.doctor

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.room.*
import com.si7ateck.dz.ui.types.City
import com.si7ateck.dz.ui.types.Specialty

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

    @Query("SELECT _city || _street   FROM doctor_table D ,_location_table L WHERE D._id_firebase=L._id_firebase and D._id_firebase LIKE :address Limit 1 ")
    fun getAddress(address:String): LiveData<String>

    @Query("SELECT _sunday|| '\$?\$' || _monday || '\$?\$' || _thursday || '\$?\$' || _wednesday || '\$?\$' || _tuesday || '\$?\$' || _friday || '\$?\$' || _saturday  FROM doctor_table D ,_working_time_table W WHERE D._id_firebase=W._id_firebase and D._id_firebase LIKE :id Limit 1 ")
    fun getWT(id:String): LiveData<String>

    @Query("SELECT * FROM doctor_table D WHERE D._speciality LIKE :specialty and D._name LIKE :name and D._id_firebase = ( SELECT _id_firebase FROM _location_table L WHERE L._city LIKE :city ) ")
    fun searchDoctor(specialty: Specialty,name:String,city: City) :  LiveData<List<Doctor>>
}