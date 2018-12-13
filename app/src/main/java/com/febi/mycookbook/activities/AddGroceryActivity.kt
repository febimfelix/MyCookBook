package com.febi.mycookbook.activities

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import com.febi.mycookbook.R
import com.febi.mycookbook.databinding.ActivityAddGroceryBinding
import com.febi.mycookbook.datastructures.GroceryItem
import com.febi.mycookbook.datastructures.GroceryViewModel

class AddGroceryActivity : AppCompatActivity() {
    lateinit var groceryViewModel : GroceryViewModel
    private lateinit var addGroceryBinding: ActivityAddGroceryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addGroceryBinding   = DataBindingUtil.setContentView(this, R.layout.activity_add_grocery)

        groceryViewModel    = ViewModelProviders.of(this).get(GroceryViewModel::class.java)
    }

    fun addNewItem(view : View) {
        addGroceryBinding.idAddGroceryTitleEdit.clearFocus()
        val inflater        = LayoutInflater.from(this).inflate(R.layout.grocery_item_add_layout, null)
        addGroceryBinding.idAddGroceryInsertView.addView(inflater)

        val editText : EditText = inflater.findViewById(R.id.id_add_grocery_item_add_field)
        editText.requestFocus()
        editText.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                addNewItem(view)
                return@setOnEditorActionListener true
            }
            false
        }
    }

    fun saveGroceryItem(view: View) {
        val groceryTitle = addGroceryBinding.idAddGroceryTitleEdit.text.toString()
        if(groceryTitle.isNotEmpty()) {
            var itemsList : MutableList<String> = mutableListOf()

            val childCount = addGroceryBinding.idAddGroceryInsertView.childCount
            if(childCount > 0) {
                for (i in 0..childCount) {
                    if(addGroceryBinding.idAddGroceryInsertView.getChildAt(i) != null) {
                        val itemView =
                            addGroceryBinding.idAddGroceryInsertView.getChildAt(i).findViewById(R.id.id_add_grocery_item_add_field) as EditText
                        val item = itemView.text.toString()
                        if (item.isNotEmpty()) {
                            itemsList.add(item)
                        }
                    }
                }
            }

            if(itemsList.isNotEmpty()) {
                var groceryItem = ""
                var iterator  = 0
                for (item in itemsList) {
                    if(iterator != 0)
                        groceryItem += ", $item"
                    else
                        groceryItem += item
                    iterator++
                }
                groceryViewModel.insert(
                    GroceryItem(
                        0,
                        addGroceryBinding.idAddGroceryTitleEdit.text.toString(),
                        groceryItem)
                )

                finish()
            } else {
                Toast.makeText(this, getString(R.string.add_grocery_items_missing), Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(this, getString(R.string.add_grocery_title_missing), Toast.LENGTH_SHORT).show()
        }
    }
}