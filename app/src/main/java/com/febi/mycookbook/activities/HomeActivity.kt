package com.febi.mycookbook.activities

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.febi.mycookbook.R
import com.febi.mycookbook.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var activityHomeBinding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.id_home_menu_settings -> {
                openSettingsScreen()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun navigateToDishesList(view : View){
        val intent = Intent(this, DishesListActivity::class.java)
        startActivity(intent)
    }

    fun navigateToPlanMeals(view : View){
        val intent = Intent(this, PlanMealsActivity::class.java)
        startActivity(intent)
    }

    fun navigateToWhatToCook(view : View){
        val intent = Intent(this, WhatToCookActivity::class.java)
        startActivity(intent)
    }

    fun navigateToMyStory(view : View){
        val intent = Intent(this, MyStoryActivity::class.java)
        startActivity(intent)
    }

    fun openSettingsScreen() {

    }
}
