package com.si7ateck.dz

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.si7ateck.dz.data.doctor.Doctor
import com.si7ateck.dz.data.doctor.DoctorDatabaseDao
import com.si7ateck.dz.data.donner.Donner
import com.si7ateck.dz.data.donner.DonnerDatabaseDao
import com.si7ateck.dz.data.gps.Location
import com.si7ateck.dz.data.gps.LocationDatabaseDao
import com.si7ateck.dz.data.pharmacy.Pharmacy
import com.si7ateck.dz.data.pharmacy.PharmacyDatabaseDao
import com.si7ateck.dz.data.Converter
import com.si7ateck.dz.data.workingtime.WTDatabaseDao
import com.si7ateck.dz.data.workingtime.WorkingTime

@Database(
        entities = [Pharmacy::class, Doctor::class, Donner::class,WorkingTime::class,Location::class],
        version = 1,
        exportSchema = false
    )
@TypeConverters(Converter::class)
    abstract class MyDataBase : RoomDatabase() {
        abstract fun pharmacyDatabaseDao(): PharmacyDatabaseDao
        abstract fun donnerDatabaseDao(): DonnerDatabaseDao
        abstract fun doctorDatabaseDao(): DoctorDatabaseDao
        abstract fun wtDatabaseDao() : WTDatabaseDao
        abstract fun locationDatabaseDao() : LocationDatabaseDao

        companion object {
            @Volatile
            private var INSTANCE: MyDataBase? = null

            fun getDatabase(context: Context): MyDataBase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                        ?: buildDatabase(context).also { INSTANCE = it }
                }

            private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                    context.applicationContext,
                    MyDataBase::class.java, "mydatabase"
                ).build()
        }

}