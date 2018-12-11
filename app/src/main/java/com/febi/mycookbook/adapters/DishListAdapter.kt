package com.febi.mycookbook.adapters

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.febi.mycookbook.R
import com.febi.mycookbook.core.AppUtils
import com.febi.mycookbook.datastructures.Dish
import java.io.File
import java.io.FileNotFoundException

class DishListAdapter internal constructor(context : Context) :
    RecyclerView.Adapter<DishListAdapter.DishListViewHolder>() {

    private val inflater : LayoutInflater   = LayoutInflater.from(context)
    private var dishes                      = emptyList<Dish>()
    private val contentResolver : ContentResolver = context.contentResolver

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DishListViewHolder {
        val itemView                        = inflater.inflate(R.layout.dish_item_layout, p0, false)
        return DishListViewHolder(itemView)
    }

    internal fun setDishes(dish : List<Dish>) {
        this.dishes                         = dish
        notifyDataSetChanged()
    }

    override fun getItemCount()             = dishes.size

    override fun onBindViewHolder(p0: DishListViewHolder, p1: Int) {
        val currentDish                     = dishes[p1]
        p0.dishNameText.text                = currentDish.name
        try {
            p0.dishImageView.setImageBitmap(MediaStore.Images.Media.getBitmap(contentResolver, Uri.fromFile(File(currentDish.image))))
        }catch (exception : FileNotFoundException) {
            exception.printStackTrace()
        }
    }

    inner class DishListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val dishNameText : TextView         = itemView.findViewById(R.id.id_dish_list_name_text)
        val dishImageView: ImageView        = itemView.findViewById(R.id.id_dish_list_image_view)
    }
}