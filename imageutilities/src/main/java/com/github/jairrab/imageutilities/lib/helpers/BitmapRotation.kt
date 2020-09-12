/* Created 6/22/2020 5:37 PM */

package com.github.jairrab.imageutilities.lib.helpers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import androidx.exifinterface.media.ExifInterface
import androidx.exifinterface.media.ExifInterface.*

class BitmapRotation(
    private val context: Context
) {
    @Suppress("MoveVariableDeclarationIntoWhen")
    fun getRotatedImageIfRequired(
        bitmap: Bitmap,
        uri: Uri
    ): Bitmap? {
        val input = context.contentResolver.openInputStream(uri) ?: return null
        val exifInterface = if (Build.VERSION.SDK_INT > 23) {
            ExifInterface(input)
        } else {
            val path = uri.path ?: return null
            ExifInterface(path)
        }
        val orientation = exifInterface.getAttributeInt(TAG_ORIENTATION, ORIENTATION_NORMAL)
        return when (orientation) {
            ORIENTATION_ROTATE_90 -> rotateImage(bitmap, 90)
            ORIENTATION_ROTATE_180 -> rotateImage(bitmap, 180)
            ORIENTATION_ROTATE_270 -> rotateImage(bitmap, 270)
            else -> bitmap
        }
    }

    private fun rotateImage(
        bitmap: Bitmap,
        degree: Int
    ): Bitmap? {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val width = bitmap.width
        val height = bitmap.height
        val rotatedImg = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true)
        bitmap.recycle()
        return rotatedImg
    }
}