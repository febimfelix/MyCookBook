package com.febi.mycookbook.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.febi.mycookbook.R
import com.febi.mycookbook.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var activityHomeBinding : ActivityHomeBinding
    var mIsPermissionGranted : Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            mIsPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 101)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            101 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    mIsPermissionGranted = true
                }
            }
        }
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
        if(!mIsPermissionGranted) return
        val intent = Intent(this, DishesListActivity::class.java)
        startActivity(intent)
    }

    fun navigateToPlanMeals(view : View){
        if(!mIsPermissionGranted) return
        val intent = Intent(this, GroceryListActivity::class.java)
        startActivity(intent)
    }

    fun navigateToWhatToCook(view : View){
        if(!mIsPermissionGranted) return
        val intent = Intent(this, WhatToCookActivity::class.java)
        startActivity(intent)
    }

    fun navigateToMyStory(view : View){
        if(!mIsPermissionGranted) return
        val intent = Intent(this, MyStoryActivity::class.java)
        startActivity(intent)
    }

    fun openSettingsScreen() {
        if(!mIsPermissionGranted) return
    }
}
