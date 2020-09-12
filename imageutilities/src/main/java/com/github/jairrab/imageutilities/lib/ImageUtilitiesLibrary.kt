package com.github.jairrab.imageutilities.lib

import android.content.Context
import android.net.Uri
import com.github.jairrab.imageutilities.ImageUtilities
import com.github.jairrab.imageutilities.lib.helpers.*
import com.github.jairrab.safutilities.SafUtilities
import java.io.File

internal class ImageUtilitiesLibrary private constructor(
    private val imageResizer: ImageResizer,
) : ImageUtilities {
    override fun getResizedImage(sourceUri: Uri, outputFile: File, width: Int, quality: Int): File {
        return imageResizer.execute(sourceUri, width, outputFile, quality)
    }

    companion object {
        fun getInstance(context: Context, safUtilities: SafUtilities): ImageUtilitiesLibrary {
            val imageResizer = ImageResizer(
                jpegUtility = JpegUtility(),
                imageDimension = ImageDimension(safUtilities),
                bitmapUtilities = BitmapUtilities(BitmapRotation(context), safUtilities)
            )
            return ImageUtilitiesLibrary(imageResizer)
        }
    }
}