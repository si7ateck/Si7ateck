package com.si7ateck.dz.repository

import com.si7ateck.dz.data.workingtime.WTDatabaseDao
import com.si7ateck.dz.data.workingtime.WorkingTime

class WorkingTimeRepository(private val wtDatabaseDao: WTDatabaseDao) {
    suspend fun insert(workingTime: WorkingTime){
        wtDatabaseDao.insert(workingTime)
    }
    suspend fun update(workingTime: WorkingTime){
        wtDatabaseDao.update(workingTime)
    }
    suspend fun clear(){
        wtDatabaseDao.clear()
    }

    suspend fun delete(workingTime: WorkingTime){
        wtDatabaseDao.delete(workingTime)
    }

}