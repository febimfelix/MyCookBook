package com.febi.mycookbook.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.febi.mycookbook.R
import com.febi.mycookbook.databinding.ActivityDishesListBinding
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.febi.mycookbook.adapters.DishListAdapter
import com.febi.mycookbook.datastructures.DishViewModel

class DishesListActivity : AppCompatActivity() {
    private lateinit var dishViewModel : DishViewModel
    lateinit var activityDishesBinding : ActivityDishesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        activityDishesBinding       = DataBindingUtil.setContentView(this, R.layout.activity_dishes_list)

        dishViewModel               = ViewModelProviders.of(this).get(DishViewModel::class.java)

        val adapter                 = DishListAdapter(this)
        activityDishesBinding.idDishesListRecyclerView.adapter          = adapter
        activityDishesBinding.idDishesListRecyclerView.layoutManager    = LinearLayoutManager(this)
        activityDishesBinding.idDishesListRecyclerView.addItemDecoration(DividerItemDecoration(this, 1))

        dishViewModel.allDishes?.observe(this, Observer { dishes ->
            dishes?.let { adapter.setDishes(it) }
        })
    }

    fun navigateToAddScreen(view : View){
        val intent = Intent(this, AddFoodActivity::class.java)
        startActivity(intent)
    }
}