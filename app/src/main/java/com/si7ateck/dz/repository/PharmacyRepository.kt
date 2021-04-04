package com.si7ateck.dz.repository

import androidx.lifecycle.LiveData
import com.si7ateck.dz.data.pharmacy.Pharmacy
import com.si7ateck.dz.data.pharmacy.PharmacyDatabaseDao

class PharmacyRepository(private val pharmacyDatabaseDao: PharmacyDatabaseDao) {
    suspend fun insert(pharmacy: Pharmacy){
        pharmacyDatabaseDao.insert(pharmacy)
    }

    suspend fun update(pharmacy: Pharmacy){
        pharmacyDatabaseDao.update(pharmacy)
    }

    suspend fun clear(){
        pharmacyDatabaseDao.clear()
    }

    val getAllPharmacy: LiveData<List<Pharmacy>> = pharmacyDatabaseDao.getAllPharmacies()
}