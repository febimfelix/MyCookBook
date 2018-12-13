package com.febi.mycookbook.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.febi.mycookbook.R
import com.febi.mycookbook.adapters.GroceryListAdapter
import com.febi.mycookbook.databinding.ActivityPlanGroceryBinding
import com.febi.mycookbook.datastructures.GroceryViewModel

class GroceryListActivity : AppCompatActivity() {
    private lateinit var grocerViewModel: GroceryViewModel
    lateinit var planGroceryBinding: ActivityPlanGroceryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        planGroceryBinding  = DataBindingUtil.setContentView(this, R.layout.activity_plan_grocery)

        grocerViewModel     = ViewModelProviders.of(this).get(GroceryViewModel::class.java)

        val adapter         = GroceryListAdapter(this)
        planGroceryBinding.idGroceryListRecyclerView.adapter        = adapter
        planGroceryBinding.idGroceryListRecyclerView.layoutManager  = LinearLayoutManager(this)

        grocerViewModel.allGrocery?.observe(this, Observer { groceryItems ->
            groceryItems?.let { adapter.setGroceryList(it) }
        })
    }

    fun navigateToAddScreen(view : View) {
        val intent          = Intent(this, AddGroceryActivity::class.java)
        startActivity(intent)
    }
}