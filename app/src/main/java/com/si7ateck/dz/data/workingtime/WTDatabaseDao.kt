package com.si7ateck.dz.data.workingtime

import androidx.room.*

@Dao
interface WTDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(workingTime: WorkingTime)

    @Update
    suspend fun update(workingTime: WorkingTime)

    @Delete
    suspend fun delete(workingTime: WorkingTime)

    @Query("DELETE FROM _working_time_table")
    suspend fun clear()
}