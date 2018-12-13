package com.febi.mycookbook.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.febi.mycookbook.datastructures.Dish
import com.febi.mycookbook.datastructures.DishDAO
import com.febi.mycookbook.datastructures.GroceryDAO
import com.febi.mycookbook.datastructures.GroceryItem

@Database(entities = [Dish::class, GroceryItem::class], version = 1, exportSchema = false)
public abstract class CookBookDatabase : RoomDatabase() {
    abstract fun dishDao() : DishDAO
    abstract fun groceryItemDao() : GroceryDAO

    companion object {
        private var INSTANCE : CookBookDatabase? = null

        fun getDatabase(context: Context) : CookBookDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, CookBookDatabase::class.java,
                    "cookbook.db").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}