package com.febi.mycookbook.datastructures

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "grocery_table")
data class GroceryItem(@PrimaryKey(autoGenerate = true) val id : Int,
                       val title : String,
                       val items : String) {

}