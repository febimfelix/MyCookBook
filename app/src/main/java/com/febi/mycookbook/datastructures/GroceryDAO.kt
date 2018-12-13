package com.febi.mycookbook.datastructures

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface GroceryDAO {
    @Query("SELECT * FROM grocery_table")
    fun getAllItems() : LiveData<List<GroceryItem>>

    @Insert
    fun insertGroceryItem(groceryItem: GroceryItem)
}