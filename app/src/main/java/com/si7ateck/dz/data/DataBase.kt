package com.si7ateck.dz.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.si7ateck.dz.data.doctor.doctor
import com.si7ateck.dz.data.doctor.DoctorDatabaseDao
import com.si7ateck.dz.data.donner.Donner
import com.si7ateck.dz.data.donner.DonnerDatabaseDao
import com.si7ateck.dz.data.pharmacy.Pharmacy
import com.si7ateck.dz.data.pharmacy.PharmacyDatabaseDao

class DataBase {
    @Database(
        entities = [Pharmacy::class, doctor::class, Donner::class],
        version = 1,
        exportSchema = true
    )
    abstract class DataBase : RoomDatabase() {
        abstract val pharmacyDatabaseDao: PharmacyDatabaseDao
        abstract val donnerDatabaseDao: DonnerDatabaseDao
        abstract val doctorDatabaseDao: DoctorDatabaseDao

        companion object {
            @Volatile
            private var INSTANCE: DataBase? = null

            fun getInstance(context: Context): DataBase {
                synchronized(this) {
                    var instance = INSTANCE
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            DataBase::class.java,
                            "database"
                        ).fallbackToDestructiveMigration()
                            .build()
                        INSTANCE = instance
                    }
                    return instance
                }
            }
        }
    }
}