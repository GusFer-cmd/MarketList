package com.example.marketlist.Database

import com.example.marketlist.Exceptions.ItemException
import com.example.marketlist.Models.CategoryEnum.CategoryEnum
import com.example.marketlist.Models.Item.ItemModel
import kotlinx.coroutines.flow.Flow

class ItemRepository(private val itemDao: ItemDAO) {

    val getAll: Flow<List<ItemModel>> = itemDao.getAll()

    suspend fun getById(id: String) : ItemModel? {
        try {
            if(id.isEmpty()) {
                throw ItemException.ItemNotFoundException(id)
            }

            return itemDao.getById(id)
        }
        catch (e: ItemException) {
            throw e
        } catch (e: Exception) {
            throw ItemException.ItemUnknownException()
        }
    }

    suspend fun toggleDone(id: String) {
        try {
            if(id.isEmpty()) {
                throw ItemException.ItemNotFoundException(id)
            }

            itemDao.toogleDone(id)
        }
        catch (e: ItemException) {
            throw e
        } catch (e: Exception) {
            throw ItemException.ItemUnknownException()
        }
    }

    suspend fun create(item: ItemModel) {
        try {
            if(item.name.isBlank()) {
                throw ItemException.EmptyNameException()
            }

            if(item.quantity < 1) {
                throw ItemException.QuantityLessThanOneException()
            }

            itemDao.create(item)
        }
        catch (e: ItemException) {
            throw e
        } catch (e: Exception) {
            throw ItemException.ItemUnknownException()
        }
    }

    suspend fun update(item: ItemModel) {
        try {
            if(item.name.isBlank()) {
                throw ItemException.EmptyNameException()
            }

            if(item.quantity < 1) {
                throw ItemException.QuantityLessThanOneException()
            }

            itemDao.update(item)
        }
        catch (e: ItemException) {
            throw e
        } catch (e: Exception) {
            throw ItemException.ItemUnknownException()
        }
    }

    suspend fun delete(id: String) {
        try {
            if(id.isEmpty()) {
                throw ItemException.ItemNotFoundException(id)
            }

            itemDao.delete(id)
        }
        catch (e: ItemException) {
            throw e
        } catch (e: Exception) {
            throw ItemException.ItemUnknownException()
        }
    }

    suspend fun clearAll() {
        try {
            itemDao.clearAll()
        }
        catch (e: Exception) {
            throw ItemException.ItemUnknownException()
        }
    }
}