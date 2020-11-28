package com.github.jairrab.imageutilities

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.fragment.app.Fragment
import com.github.jairrab.imageutilities.lib.ImageUtilitiesLibrary
import com.github.jairrab.safutilities.SafUtilities
import java.io.File

interface ImageUtilities {
    fun getJpeg(
        bitmap: Bitmap?,
        outputFile: File,
        quality: Int
    ): File

    fun getPng(
        bitmap: Bitmap?,
        outputFile: File,
        quality: Int
    ): File

    fun getPngBase64(
        bitmap: Bitmap,
        quality: Int,
    ): String

    fun getResizedImage(
        sourceUri: Uri,
        outputFile: File,
        width: Int,
        quality: Int
    ): File

    /**
     * Use [ImageUtilities.getResizedImage] instead
     */
    @Deprecated("Not using latest libraries")
    fun getResizedImage(outputFile: File): File

    fun openCamera(
        fragment: Fragment,
        file: File,
        requestCode: Int,
        fileAuthority: String,
    )

    fun openFileExternally(file: File, fileAuthority: String)

    companion object {
        fun getInstance(context: Context): ImageUtilities {
            val safUtilities = SafUtilities.getInstance(context)
            return ImageUtilitiesLibrary.getInstance(context, safUtilities)
        }
    }
}

