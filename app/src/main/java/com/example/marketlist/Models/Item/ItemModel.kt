package com.example.marketlist.Models.Item

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marketlist.Models.CategoryEnum.CategoryEnum

fun shortUuid() : String {
    return java.util.UUID.randomUUID().toString().substring(0, 8)
}

@Entity
data class ItemModel (
    @PrimaryKey(autoGenerate = false)
    var id : String = shortUuid(),
    val name: String = "",
    val category: CategoryEnum,
    val quantity: Int = 1,
    val checked: Boolean = false
)