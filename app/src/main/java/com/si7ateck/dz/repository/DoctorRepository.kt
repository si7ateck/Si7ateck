package com.si7ateck.dz.repository

import androidx.lifecycle.LiveData

import com.si7ateck.dz.data.doctor.doctor
import com.si7ateck.dz.data.doctor.DoctorDatabaseDao

class DoctorRepository(private val doctorDatabaseDao: DoctorDatabaseDao) {

    suspend fun insert(doctor: doctor){
        doctorDatabaseDao.insert(doctor)
    }

    suspend fun update(doctor: doctor){
        doctorDatabaseDao.update(doctor)
    }

    suspend fun clear(){
        doctorDatabaseDao.clear()
    }

    val getAllDoctors: LiveData<List<doctor>> = doctorDatabaseDao.getAllDoctors()
}