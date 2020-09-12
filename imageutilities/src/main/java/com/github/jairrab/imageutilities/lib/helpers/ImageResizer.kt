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
import java.io.File

@Suppress("MemberVisibilityCanBePrivate")
class ImageResizer(
    private val jpegUtility: JpegUtility,
    private val imageDimension: ImageDimension,
    private val bitmapUtilities: BitmapUtilities
) {
    fun execute(
        sourceUri: Uri,
        width: Int,
        outputFile: File,
        quality: Int
    ): File {
        val needsResizing = imageDimension.getImageWidth(sourceUri) > width
        val bitmap = if (needsResizing) {
            bitmapUtilities.getResizedBitmap(sourceUri, width)
        } else {
            bitmapUtilities.getBitmap(sourceUri)
        }
        return jpegUtility.getJpeg(bitmap, outputFile, quality)
    }
}