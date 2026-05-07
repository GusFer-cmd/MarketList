package com.example.marketlist.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.marketlist.Models.CategoryEnum.CategoryEnum
import com.example.marketlist.Models.Item.ItemModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDAO {

    @Query("SELECT * FROM ItemModel ORDER BY name ASC")
    fun getAll() : Flow<List<ItemModel>>

    @Query("SELECT * FROM ItemModel WHERE id = :id")
    suspend fun getById(id: String) : ItemModel?

    @Query("UPDATE ItemModel SET checked = NOT checked WHERE id = :id")
    suspend fun toogleDone(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(item: ItemModel)

    @Update
    suspend fun update(item: ItemModel)

    @Query("DELETE FROM ItemModel WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM ItemModel")
    suspend fun clearAll()
}