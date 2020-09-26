package com.github.jairrab.imageutilities

import android.content.Context
import android.net.Uri
import androidx.fragment.app.Fragment
import com.github.jairrab.imageutilities.lib.ImageUtilitiesLibrary
import com.github.jairrab.safutilities.SafUtilities
import java.io.File

interface ImageUtilities {
    fun openCamera(
        fragment: Fragment,
        file: File,
        requestCode: Int,
        fileAuthority: String,
    )

    fun getResizedImage(
        sourceUri: Uri,
        outputFile: File,
        width: Int,
        quality: Int
    ): File

    @Deprecated("Use other instead")
    fun getResizedImage(outputFile: File): File

    fun openFileExternally(file: File, fileAuthority: String)

    companion object {
        fun getInstance(context: Context): ImageUtilities {
            val safUtilities = SafUtilities.getInstance(context)
            return ImageUtilitiesLibrary.getInstance(context, safUtilities)
        }
    }
}

