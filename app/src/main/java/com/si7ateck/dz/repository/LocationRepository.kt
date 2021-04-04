package com.si7ateck.dz.repository

import com.si7ateck.dz.data.gps.Location
import com.si7ateck.dz.data.gps.LocationDatabaseDao


class LocationRepository(private val locationDatabaseDao: LocationDatabaseDao) {
    suspend fun insert(location: Location){
        locationDatabaseDao.insert(location)
    }

    suspend fun update(location: Location){
        locationDatabaseDao.update(location)
    }

    suspend fun clear(){
        locationDatabaseDao.clear()
    }


    suspend fun delete(location: Location){
        locationDatabaseDao.delete(location)
    }

}