package com.si7ateck.dz.repository

import androidx.lifecycle.LiveData
import com.si7ateck.dz.data.doctor.Doctor
import com.si7ateck.dz.data.donner.Donner
import com.si7ateck.dz.data.donner.DonnerDatabaseDao

class DonnerRepository (private  val donnerDatabaseDao: DonnerDatabaseDao) {
    suspend fun insert(donner: Donner){
        donnerDatabaseDao.insert(donner)
    }

    suspend fun update(donner: Donner){
        donnerDatabaseDao.update(donner)
    }

    suspend fun clear(){
        donnerDatabaseDao.clear()
    }

    val getAllDoctors: LiveData<List<Donner>> = donnerDatabaseDao.getAllDonners()
}