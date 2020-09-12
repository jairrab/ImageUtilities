package com.github.jairrab.imageutilities.lib

import android.content.Context
import android.net.Uri
import com.github.jairrab.imageutilities.ImageUtility
import com.github.jairrab.imageutilities.lib.helpers.*
import com.github.jairrab.safutilities.SafUtilities
import java.io.File

internal class ImageUtilityLibrary private constructor(
    private val imageResizer: ImageResizer,
) : ImageUtility {
    override fun getResizedImage(sourceUri: Uri, outputFile: File, width: Int, quality: Int): File {
        return imageResizer.execute(sourceUri, width, outputFile, quality)
    }

    companion object {
        fun getInstance(context: Context, safUtilities: SafUtilities): ImageUtilityLibrary {
            val imageResizer = ImageResizer(
                jpegUtility = JpegUtility(),
                imageDimension = ImageDimension(safUtilities),
                bitmapUtilities = BitmapUtilities(BitmapRotation(context), safUtilities)
            )
            return ImageUtilityLibrary(imageResizer)
        }
    }
}