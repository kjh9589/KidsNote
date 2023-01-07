package com.kjs.data.db.picsum

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PicsumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(picsum: PicsumEntity)

    @Query("SELECT isLike FROM picsum_table WHERE id = :id")
    suspend fun getPicsum(id: String): Boolean?
}