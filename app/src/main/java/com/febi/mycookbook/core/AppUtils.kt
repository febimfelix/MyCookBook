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
    }
}