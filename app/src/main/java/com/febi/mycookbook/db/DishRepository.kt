package com.febi.mycookbook.db

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.febi.mycookbook.datastructures.Dish
import com.febi.mycookbook.datastructures.DishDAO

class DishRepository(private val dishDAO: DishDAO) {
    val allDishes : LiveData<List<Dish>> = dishDAO.getAllDishes()

    @WorkerThread
    suspend fun insert(dish : Dish){
        dishDAO.insertDish(dish)
    }
}