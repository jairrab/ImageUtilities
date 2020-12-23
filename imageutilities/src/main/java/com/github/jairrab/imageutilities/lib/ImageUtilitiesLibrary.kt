package com.github.jairrab.imageutilities.lib

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.fragment.app.Fragment
import com.github.jairrab.imageutilities.ImageUtilities
import com.github.jairrab.imageutilities.lib.helpers.*
import com.github.jairrab.imageutilities.lib.helpers.deprecated.OldImageResizer
import com.github.jairrab.safutilities.SafUtilities
import java.io.File

internal class ImageUtilitiesLibrary private constructor(
    private val cameraHelper: CameraHelper,
    private val imageResizer: ImageResizer,
    private val bitmapUtility: BitmapUtility,
    private val oldImageResizer: OldImageResizer,
    private val openFileExternally: OpenFileExternally,
) : ImageUtilities {
    override fun getJpeg(
        bitmap: Bitmap?,
        outputFile: File,
        quality: Int
    ): File {
        return bitmapUtility.getJpeg(bitmap, outputFile, quality)
    }

    override fun getPng(bitmap: Bitmap?, outputFile: File, quality: Int): File {
        return bitmapUtility.getPng(bitmap, outputFile, quality)
    }

    override fun getPngBase64(bitmap: Bitmap, quality: Int): String {
        return bitmapUtility.getPngBase64(bitmap, quality)
    }

    override fun getJpgBase64(bitmap: Bitmap, quality: Int): String {
        return bitmapUtility.getJpgBase64(bitmap, quality)
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

    override fun openCamera(
        fragment: Fragment,
        file: File,
        requestCode: Int,
        fileAuthority: String,
    ) {
        cameraHelper.openCamera(fragment, file, requestCode, fileAuthority)
    }

    override fun openFileExternally(file: File, fileAuthority: String) {
        openFileExternally.execute(file, fileAuthority)
    }

    companion object {
        const val LOG_TAG = "ImageUtilities"

        fun getInstance(context: Context, safUtilities: SafUtilities): ImageUtilitiesLibrary {
            val bitmapUtility = BitmapUtility()
            return ImageUtilitiesLibrary(
                cameraHelper = CameraHelper(),
                imageResizer = ImageResizer(
                    bitmapUtility = bitmapUtility,
                    imageDimension = ImageDimension(safUtilities),
                    bitmapUtilities = BitmapUtilities(BitmapRotation(context), safUtilities)
                ),
                bitmapUtility = BitmapUtility(),
                oldImageResizer = OldImageResizer(context),
                openFileExternally = OpenFileExternally(context),
            )
        }
    }
}