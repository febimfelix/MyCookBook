package com.febi.mycookbook.datastructures

import android.arch.persistence.room.Entity
import java.util.*

@Entity(tableName = "dish_table")
data class Dish(val name : String, val date : Date, val desc : String, val image : ByteArray){
}