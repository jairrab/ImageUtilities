/* Created 6/22/2020 5:37 PM */

package com.github.jairrab.imageutilities.lib.helpers

import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream

class JpegUtility {
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
}