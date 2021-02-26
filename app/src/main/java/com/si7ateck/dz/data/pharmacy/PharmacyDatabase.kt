package com.si7ateck.dz.data.pharmacy

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.si7ateck.dz.data.doctor.Doctor
import com.si7ateck.dz.data.doctor.DoctorDatabaseDao

@Database(entities = [Pharmacy::class], version = 1, exportSchema = false)
abstract class PharmacyDatabase : RoomDatabase() {
    abstract val pharmacyDatabaseDao: PharmacyDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: PharmacyDatabase? = null

        fun getInstance(context: Context): PharmacyDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PharmacyDatabase::class.java,
                        "pharmacy_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}