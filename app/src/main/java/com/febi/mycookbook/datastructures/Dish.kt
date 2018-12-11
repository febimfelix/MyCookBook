package com.febi.mycookbook.datastructures

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity(tableName = "dish_table")
data class Dish(@PrimaryKey (autoGenerate = true) val id : Int,
                val name : String,
                val date : String,
                val desc : String,
                val image : String){
}