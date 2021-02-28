package com.si7ateck.dz.data.donner

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DonnerDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(donner: Donner)

    @Update
    suspend fun update(donner: Donner)

    @Query("DELETE FROM donner_table")
    suspend fun clear()

    @Query("SELECT * FROM donner_table ORDER BY _Id DESC" )
    fun getAllDonners(): LiveData<List<Donner>>
}