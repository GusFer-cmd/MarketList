package com.example.marketlist.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.marketlist.Models.Item.ItemModel

@Database(entities = [ItemModel::class], version = 2)
@TypeConverters(CategoryConverter::class)
abstract class ItemDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "DB_ITEM"
    }

    abstract fun getItemDAO() : ItemDAO
}