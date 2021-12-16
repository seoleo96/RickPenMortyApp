package com.example.rickpenmortyapp.data.local


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CharacterCache.CharacterCacheModel::class], version = 1, exportSchema = false)
abstract class RickDB() : RoomDatabase() {

    abstract fun rickDao(): RickDao

    companion object {
        @Volatile
        private var INSTANCE: RickDB? = null
        fun getDatabase(context: Context): RickDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RickDB::class.java,
                    "rick_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}