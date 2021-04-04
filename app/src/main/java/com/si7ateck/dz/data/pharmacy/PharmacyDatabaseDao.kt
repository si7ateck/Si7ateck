package com.si7ateck.dz.data.pharmacy

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PharmacyDatabaseDao {
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(pharmacy: Pharmacy)

        @Update
        suspend fun update(pharmacy: Pharmacy)

        @Query("DELETE FROM pharmacy_table")
        suspend fun clear()

        @Query("SELECT * FROM pharmacy_table ORDER BY _Id DESC" )
        fun getAllPharmacies(): LiveData<List<Pharmacy>>

}