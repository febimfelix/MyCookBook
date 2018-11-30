package com.febi.mycookbook.datastructures

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.febi.mycookbook.db.CookBookDatabase
import com.febi.mycookbook.db.DishRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DishViewModel(application: Application) : AndroidViewModel(application) {
    private val parentJob: Job = Job()
    private val coroutineContext : CoroutineContext
        get()           = parentJob + Dispatchers.Main
    private val scope   = CoroutineScope(coroutineContext)

    private var dishRepository : DishRepository
    var allDishes : LiveData<List<Dish>> ?= null

    init {
        val dishDao     = CookBookDatabase.getDatabase(application).dishDao()
        dishRepository  =  DishRepository(dishDao)
        allDishes       = dishRepository.allDishes
    }

    fun insert(dish : Dish) = scope.launch(Dispatchers.IO) {
        dishRepository.insert(dish)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}