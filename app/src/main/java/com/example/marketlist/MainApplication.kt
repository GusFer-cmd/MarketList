package com.example.marketlist

import android.app.Application
import androidx.room.Room
import com.example.marketlist.Database.ItemDatabase

class MainApplication : Application() {

    companion object {
        lateinit var itemDatabase: ItemDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()

        itemDatabase = Room.databaseBuilder(
            applicationContext,
            ItemDatabase::class.java,
            ItemDatabase.DB_NAME
        )
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }
}