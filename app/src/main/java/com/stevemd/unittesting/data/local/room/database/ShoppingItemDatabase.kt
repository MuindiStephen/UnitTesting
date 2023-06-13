package com.stevemd.unittesting.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stevemd.unittesting.data.local.room.dao.ShoppingDao
import com.stevemd.unittesting.data.local.room.entity.ShoppingItem


@Database([ShoppingItem::class], exportSchema = false, version = 1)
abstract class ShoppingItemDatabase : RoomDatabase(){
    abstract fun shoppingDao(): ShoppingDao
}