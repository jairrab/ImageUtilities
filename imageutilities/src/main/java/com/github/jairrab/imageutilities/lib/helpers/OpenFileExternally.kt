package com.github.jairrab.imageutilities.lib.helpers

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File

class OpenFileExternally(private val context: Context) {

    //https://stackoverflow.com/a/63937039
    fun execute(file: File, fileAuthority: String) {
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(context, fileAuthority, file)
        } else Uri.fromFile(file)

        val target = Intent(Intent.ACTION_VIEW)
        target.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        target.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        val type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(file.extension)
        target.setDataAndType(uri, type)

        val intent = Intent.createChooser(target, null)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        //required if launching an activity from a non-activity context
        if (context !is Activity) intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        context.startActivity(intent)
    }

    @Deprecated("This was the old method that stopped working recently")
    fun openFileExternally(
        application: Application,
        file: File,
        mimeType: String?,
        fileAuthority: String
    ) {
        val intent: Intent

        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(context, fileAuthority, file)
        } else Uri.fromFile(file)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent = Intent(Intent.ACTION_VIEW, uri)

            val resInfoList = application.packageManager
                .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)

            for (resolveInfo in resInfoList) {
                val packageName = resolveInfo.activityInfo.packageName
                val modeFlags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION or
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                application.grantUriPermission(packageName, uri, modeFlags)
            }
        } else {
            intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(uri, mimeType)
        }

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        if (intent.resolveActivity(application.packageManager) != null) {
            application.startActivity(intent)
        } else {
            val text = "No external app capable of opening this file detected."
            Toast.makeText(application, text, Toast.LENGTH_LONG).show()
        }
    }
}