package com.si7ateck.dz.data.doctor

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Doctor::class], version = 1, exportSchema = false)
abstract class DoctorDatabase : RoomDatabase() {
    abstract val doctorDatabaseDao: DoctorDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: DoctorDatabase? = null

        fun getInstance(context: Context): DoctorDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DoctorDatabase::class.java,
                        "doctor_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}