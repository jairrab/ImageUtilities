/* Created 6/22/2020 5:37 PM */

package com.github.jairrab.imageutilities.lib.helpers

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class BitmapUtility {
    fun getJpeg(
        bitmap: Bitmap?,
        outputFile: File,
        quality: Int
    ): File {
        val outputStream = FileOutputStream(outputFile)
        bitmap?.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        bitmap?.recycle()
        outputStream.close()
        return outputFile
    }

    fun getPng(
        bitmap: Bitmap?,
        outputFile: File,
        quality: Int
    ): File {
        val outputStream = FileOutputStream(outputFile)
        bitmap?.compress(Bitmap.CompressFormat.PNG, quality, outputStream)
        bitmap?.recycle()
        outputStream.close()
        return outputFile
    }

    fun getPngBase64(
        bitmap: Bitmap,
        quality: Int,
    ): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, quality, outputStream)
        val bytes = outputStream.toByteArray()
        return Base64.encodeToString(bytes, Base64.NO_WRAP)
    }

    fun getJpgBase64(
        bitmap: Bitmap,
        quality: Int,
    ): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        val bytes = outputStream.toByteArray()
        return Base64.encodeToString(bytes, Base64.NO_WRAP)
    }
}