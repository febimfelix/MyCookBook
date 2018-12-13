package com.febi.mycookbook.db

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.febi.mycookbook.datastructures.GroceryDAO
import com.febi.mycookbook.datastructures.GroceryItem

class GroceryRepository(private val groceryDAO: GroceryDAO) {
    val allGroceryItems : LiveData<List<GroceryItem>> =  groceryDAO.getAllItems()

    @WorkerThread
    suspend fun insertGrocery(groceryItem: GroceryItem) {
        groceryDAO.insertGroceryItem(groceryItem)
    }
}