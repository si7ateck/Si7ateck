package com.si7ateck.dz.data.gps

import androidx.room.*

@Dao
interface LocationDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(location: Location)

    @Update
    suspend fun update(location: Location)

    @Query("DELETE FROM _location_table")
    suspend fun clear()
}