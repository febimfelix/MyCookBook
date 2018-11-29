package com.febi.mycookbook

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.DialogFragment
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.DatePicker
import com.febi.mycookbook.AddFoodActivity.DatePickerFragment.OnDateSelectionListener
import com.febi.mycookbook.databinding.ActivityAddFoodBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class AddFoodActivity : AppCompatActivity(){
    val REQUEST_TAKE_PHOTO = 1

    lateinit var mCurrentPhotoPath : String
    lateinit var addFoodDataBinding : ActivityAddFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addFoodDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_food)

        addFoodDataBinding.idAddFoodDate.setOnClickListener(dateClickListener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            val imageBitmap = data!!.extras.get("data") as Bitmap
            addFoodDataBinding.idAddFoodAvatar.setImageBitmap(imageBitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
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
        dispatchTakePictureIntent()
    }

    fun saveDish(view : View) {

    }

    private fun createImageFile(): File {
        val timeStamp: String   = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File    = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir).apply {
            mCurrentPhotoPath   = absolutePath
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }

                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.febi.mycookbook",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    private fun setPic() {
        val targetW: Int            = addFoodDataBinding.idAddFoodAvatar.width
        val targetH: Int            = addFoodDataBinding.idAddFoodAvatar.height

        val bmOptions               = BitmapFactory.Options().apply {
            inJustDecodeBounds      = true
            BitmapFactory.decodeFile(mCurrentPhotoPath, this)
            val photoW: Int         = outWidth
            val photoH: Int         = outHeight

            val scaleFactor: Int    = Math.min(photoW / targetW, photoH / targetH)

            inJustDecodeBounds      = false
            inSampleSize            = scaleFactor
        }
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions)?.also { bitmap ->
            addFoodDataBinding.idAddFoodAvatar.setImageBitmap(bitmap)
        }
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