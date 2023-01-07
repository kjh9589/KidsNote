package com.kjs.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kjs.data.db.picsum.PicsumDao
import com.kjs.data.db.picsum.PicsumEntity

@Database(
    entities = [PicsumEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun picsumDao(): PicsumDao

    companion object {
        const val databaseName = "PicsumDb"
    }
}