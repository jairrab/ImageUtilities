/* Created 6/22/2020 5:37 PM */

package com.github.jairrab.imageutilities.lib.helpers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.github.jairrab.safutilities.SafUtilities

class BitmapUtilities(
    private val bitmapRotation: BitmapRotation,
    private val safUtilities: SafUtilities
) {
    fun getResizedBitmap(
        uri: Uri,
        width: Int
    ): Bitmap? {
        val fileInputStream = safUtilities.getInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(fileInputStream)
        val newHeight = (bitmap.height * (width.toFloat() / bitmap.width)).toInt()
        fileInputStream?.close()
        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, width, newHeight, true)
        return bitmapRotation.getRotatedImageIfRequired(resizedBitmap, uri)
    }

    fun getBitmap(uri: Uri): Bitmap? {
        val fileInputStream = safUtilities.getInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(fileInputStream)
        fileInputStream?.close()
        return bitmap
    }
}