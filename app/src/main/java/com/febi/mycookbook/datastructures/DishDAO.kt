package com.febi.mycookbook.datastructures

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface DishDAO {
    @Query("SELECT * FROM dish_table ORDER BY date")
    fun getAllDishes() : LiveData<List<Dish>>

    @Insert
    fun insertDish(dish: Dish)


}