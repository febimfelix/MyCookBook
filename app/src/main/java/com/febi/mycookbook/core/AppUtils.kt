package com.febi.mycookbook.core

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.sql.Blob
import java.text.SimpleDateFormat
import java.util.*

class AppUtils{
    companion object {
        fun getBitmapFromBlob(byteArray : ByteArray) : Bitmap {
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        }

        fun getDateFromString(dateString : String) : java.util.Date {
            val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return format.parse(dateString)
        }

        fun getByteArrayFromDrawable(bitmap: Bitmap) : ByteArray {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

            return byteArrayOutputStream.toByteArray()
        }
    }
}