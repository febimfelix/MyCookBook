package com.febi.mycookbook

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.febi.mycookbook.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var activityHomeBinding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    fun navigateToAddScreen(view : View){
        val intent = Intent(this, AddFoodActivity::class.java)
        startActivity(intent)
    }
}
