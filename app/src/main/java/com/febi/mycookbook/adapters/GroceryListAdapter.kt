package com.febi.mycookbook.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.febi.mycookbook.R
import com.febi.mycookbook.datastructures.GroceryItem

class GroceryListAdapter internal constructor(context : Context) :
            RecyclerView.Adapter<GroceryListAdapter.GroceryListViewHolder>() {

    private val inflater: LayoutInflater    = LayoutInflater.from(context)
    private var groceryItems                = emptyList<GroceryItem>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GroceryListViewHolder {
        val itemView                = inflater.inflate(R.layout.grocery_item_layout, p0, false)

        return GroceryListViewHolder(itemView)
    }

    internal fun setGroceryList(groceryList : List<GroceryItem>) {
        this.groceryItems           = groceryList
        notifyDataSetChanged()
    }

    override fun getItemCount()     = groceryItems.size

    override fun onBindViewHolder(p0: GroceryListViewHolder, p1: Int) {
        val currentItem             = groceryItems[p1]
        p0.groceryItemTitle.text    = currentItem.title
        p0.groceryItemsText.text    = currentItem.items
    }

    inner class GroceryListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val groceryItemTitle : TextView     = itemView.findViewById(R.id.id_grocery_list_item_title)
        val groceryItemsText : TextView     = itemView.findViewById(R.id.id_grocery_list_items_text)
    }
}