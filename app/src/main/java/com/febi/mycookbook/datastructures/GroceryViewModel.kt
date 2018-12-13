package com.febi.mycookbook.datastructures

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.febi.mycookbook.db.CookBookDatabase
import com.febi.mycookbook.db.GroceryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class GroceryViewModel(application: Application) : AndroidViewModel(application) {
    private val parentJob   = Job()
    private val coroutineContext : CoroutineContext
    get()                   = parentJob + Dispatchers.Main
    private val scope       = CoroutineScope(coroutineContext)

    private var groceryRepository : GroceryRepository
    var allGrocery : LiveData<List<GroceryItem>> ?= null

    init {
        val groceryDAO      = CookBookDatabase.getDatabase(application).groceryItemDao()
        groceryRepository   = GroceryRepository(groceryDAO)
        allGrocery          = groceryRepository.allGroceryItems
    }

    fun insert(groceryItem: GroceryItem) = scope.launch(Dispatchers.IO) {
        groceryRepository.insertGrocery(groceryItem)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}