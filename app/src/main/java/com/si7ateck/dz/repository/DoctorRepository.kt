package com.si7ateck.dz.repository

import androidx.lifecycle.LiveData

import com.si7ateck.dz.data.doctor.Doctor
import com.si7ateck.dz.data.doctor.DoctorDatabaseDao

class DoctorRepository(private val doctorDatabaseDao: DoctorDatabaseDao) {

    suspend fun insert(doctor: Doctor){
        doctorDatabaseDao.insert(doctor)
    }

    suspend fun update(doctor: Doctor){
        doctorDatabaseDao.update(doctor)
    }

    suspend fun clear(){
        doctorDatabaseDao.clear()
    }

    val getAllDoctors: LiveData<List<Doctor>> = doctorDatabaseDao.getAllDoctors()
}