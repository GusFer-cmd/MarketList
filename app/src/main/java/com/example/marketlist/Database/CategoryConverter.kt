package com.example.marketlist.Database

import androidx.room.TypeConverter
import com.example.marketlist.Models.CategoryEnum.CategoryEnum

class CategoryConverter {

    @TypeConverter
    fun fromCategory(category: CategoryEnum): String {
        return category.name
    }

    @TypeConverter
    fun toCategory(category: String): CategoryEnum {
        return CategoryEnum.valueOf(category)
    }
}