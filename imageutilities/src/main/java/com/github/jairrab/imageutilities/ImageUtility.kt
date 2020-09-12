package com.github.jairrab.imageutilities

import android.content.Context
import android.net.Uri
import com.github.jairrab.imageutilities.lib.ImageUtilityLibrary
import com.github.jairrab.safutilities.SafUtilities
import java.io.File

interface ImageUtility {
    fun getResizedImage(sourceUri: Uri, outputFile: File, width: Int, quality: Int): File

    companion object {
        fun getInstance(context: Context): ImageUtility {
            val safUtilities = SafUtilities.getInstance(context)
            return ImageUtilityLibrary.getInstance(context, safUtilities)
        }
    }
}

