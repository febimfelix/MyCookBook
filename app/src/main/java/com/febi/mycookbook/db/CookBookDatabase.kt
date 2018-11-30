package com.febi.mycookbook.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.febi.mycookbook.datastructures.Dish
import com.febi.mycookbook.datastructures.DishDAO

@Database(entities = [Dish::class], version = 1)
public abstract class CookBookDatabase : RoomDatabase() {
    abstract fun dishDao() : DishDAO

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