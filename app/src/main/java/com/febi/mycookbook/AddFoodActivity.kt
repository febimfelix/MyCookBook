package com.febi.mycookbook

import android.app.DatePickerDialog
import android.app.Dialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.DatePicker
import com.febi.mycookbook.AddFoodActivity.DatePickerFragment.OnDateSelectionListener
import com.febi.mycookbook.databinding.ActivityAddFoodBinding
import java.util.*

class AddFoodActivity : AppCompatActivity(){
    lateinit var addFoodDataBinding : ActivityAddFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addFoodDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_food)

        addFoodDataBinding.idAddFoodDate.setOnClickListener(dateClickListener)
    }

    private val dateClickListener : View.OnClickListener = View.OnClickListener {
        val newFragment = DatePickerFragment()
        newFragment.setListener(dateSelectedListener)
        newFragment.show(supportFragmentManager, "datePicker")
    }

    private val dateSelectedListener : OnDateSelectionListener = object : OnDateSelectionListener {
        override fun onDateSet(date: String) {
            addFoodDataBinding.idAddFoodDate.setText(date)
        }
    }

    fun uploadImage(view : View) {

    }

    fun saveDish(view : View) {

    }

    class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
        lateinit var onDateListener : OnDateSelectionListener

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c       = Calendar.getInstance()
            val year    = c.get(Calendar.YEAR)
            val month   = c.get(Calendar.MONTH)
            val day     = c.get(Calendar.DAY_OF_MONTH)

            return DatePickerDialog(activity, this, year, month, day);
        }

        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            onDateListener.onDateSet(TextUtils.concat("$dayOfMonth/$month/$year") as String)
        }

        fun setListener(onDateSelection : OnDateSelectionListener) {
            onDateListener = onDateSelection
        }

        interface OnDateSelectionListener {
            fun onDateSet(date : String)
        }

    }
}