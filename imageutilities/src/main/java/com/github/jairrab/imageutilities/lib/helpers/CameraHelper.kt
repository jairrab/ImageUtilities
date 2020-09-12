package com.github.jairrab.imageutilities.lib.helpers

import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import java.io.File

class CameraHelper {
    fun openCamera(
        fragment: Fragment,
        file: File,
        requestCode: Int,
        fileAuthority: String,
    ) = fragment.run {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            val uri = getUri(file, intent, fileAuthority)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            fragment.startActivityForResult(intent, requestCode)
        }
    }

    private fun Fragment.getUri(
        file: File,
        intent: Intent,
        fileAuthority: String
    ): Uri? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val uri = FileProvider.getUriForFile(requireContext(), fileAuthority, file)
            requireContext().packageManager
                .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
                .forEach { resolveInfo ->
                    val packageName = resolveInfo.activityInfo.packageName
                    val modeFlags = FLAG_GRANT_WRITE_URI_PERMISSION or FLAG_GRANT_READ_URI_PERMISSION
                    requireContext().grantUriPermission(packageName, uri, modeFlags)
                }
            uri
        } else {
            Uri.fromFile(file)
        }
    }
}