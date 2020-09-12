package com.github.jairrab.imageutilities.lib

import android.content.Context
import android.net.Uri
import androidx.fragment.app.Fragment
import com.github.jairrab.imageutilities.ImageUtilities
import com.github.jairrab.imageutilities.lib.helpers.*
import com.github.jairrab.imageutilities.lib.helpers.deprecated.OldImageResizer
import com.github.jairrab.safutilities.SafUtilities
import java.io.File

internal class ImageUtilitiesLibrary private constructor(
    private val cameraHelper: CameraHelper,
    private val oldImageResizer: OldImageResizer,
    private val imageResizer: ImageResizer,
) : ImageUtilities {
    override fun openCamera(
        fragment: Fragment,
        file: File,
        requestCode: Int,
        fileAuthority: String,
    ) {
        cameraHelper.openCamera(fragment, file, requestCode, fileAuthority)
    }

    override fun getResizedImage(
        sourceUri: Uri,
        outputFile: File,
        width: Int,
        quality: Int
    ): File {
        return imageResizer.execute(sourceUri, width, outputFile, quality)
    }

    override fun getResizedImage(outputFile: File): File {
         oldImageResizer.generatePictureFile(outputFile)
        return outputFile
    }

    companion object {
        fun getInstance(context: Context, safUtilities: SafUtilities): ImageUtilitiesLibrary {
            return ImageUtilitiesLibrary(
                cameraHelper = CameraHelper(),
                oldImageResizer = OldImageResizer(context),
                imageResizer = ImageResizer(
                    jpegUtility = JpegUtility(),
                    imageDimension = ImageDimension(safUtilities),
                    bitmapUtilities = BitmapUtilities(BitmapRotation(context), safUtilities)
                )
            )
        }
    }
}