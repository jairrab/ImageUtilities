/* Created 6/22/2020 5:37 PM */

package com.github.jairrab.imageutilities.lib.helpers

import android.graphics.BitmapFactory
import android.net.Uri
import com.github.jairrab.safutilities.SafUtilities
import java.io.FileInputStream

class ImageDimension(
    private val safUtilities: SafUtilities
) {
    fun getImageWidth(sourceUri: Uri): Int {
        val inputStream = safUtilities.getInputStream(sourceUri)
        val options = BitmapFactory.Options().also { it.inJustDecodeBounds = true }
        BitmapFactory.decodeStream(inputStream, null, options)
        inputStream?.close()
        return options.outWidth
    }

    fun getWidthAndHeight(
        text: String,
        inputStream: FileInputStream?
    ): Pair<Int, Int> {
        val options = BitmapFactory.Options().also { it.inJustDecodeBounds = true }
        BitmapFactory.decodeStream(inputStream, null, options)
        inputStream?.close()
        return Pair(options.outWidth, options.outHeight)
    }
}