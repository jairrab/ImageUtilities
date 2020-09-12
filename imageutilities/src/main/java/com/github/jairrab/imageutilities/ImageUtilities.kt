package com.github.jairrab.imageutilities

import android.content.Context
import android.net.Uri
import com.github.jairrab.imageutilities.lib.ImageUtilitiesLibrary
import com.github.jairrab.safutilities.SafUtilities
import java.io.File

interface ImageUtilities {
    fun getResizedImage(sourceUri: Uri, outputFile: File, width: Int, quality: Int): File

    companion object {
        fun getInstance(context: Context): ImageUtilities {
            val safUtilities = SafUtilities.getInstance(context)
            return ImageUtilitiesLibrary.getInstance(context, safUtilities)
        }
    }
}

