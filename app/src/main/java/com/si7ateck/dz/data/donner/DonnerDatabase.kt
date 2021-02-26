package com.si7ateck.dz.data.donner

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.si7ateck.dz.data.doctor.DoctorDatabase
import com.si7ateck.dz.data.doctor.DoctorDatabaseDao

@Database(entities = [Donner::class], version = 1, exportSchema = false)
abstract class DonnerDatabase : RoomDatabase() {
    abstract val donnerDatabaseDao: DonnerDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: DonnerDatabase? = null

        fun getInstance(context: Context): DonnerDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DonnerDatabase::class.java,
                        "donner_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}