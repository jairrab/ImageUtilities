/*
 * Copyright (C) 2020 - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Created by Antonio Barria <jaybarria@gmail.com>
 * Last modified 5/8/20 2:58 PM
 */

/* Created 4/25/2020 2:08 PM */

package com.github.jairrab.imageutilities.lib.helpers

import android.net.Uri
import android.util.Log
import com.github.jairrab.imageutilities.lib.ImageUtilitiesLibrary.Companion.LOG_TAG
import java.io.File

@Suppress("MemberVisibilityCanBePrivate")
class ImageResizer(
    private val bitmapUtility: BitmapUtility,
    private val imageDimension: ImageDimension,
    private val bitmapUtilities: BitmapUtilities
) {
    fun execute(
        sourceUri: Uri,
        width: Int,
        outputFile: File,
        quality: Int
    ): File {
        val imageWidth = imageDimension.getImageWidth(sourceUri)
        val needsResizing = imageWidth > width
        Log.d(LOG_TAG, "imageWidth: $imageWidth | maxWidth: $width | needsResizing: $needsResizing")

        val bitmap = if (needsResizing) {
            bitmapUtilities.getResizedBitmap(sourceUri, width)
        } else {
            bitmapUtilities.getBitmap(sourceUri)
        }

        return bitmapUtility.getJpeg(bitmap, outputFile, quality)
    }
}