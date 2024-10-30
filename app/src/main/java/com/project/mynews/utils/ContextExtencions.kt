package com.project.mynews.utils

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.util.concurrent.ExecutionException

suspend fun Context.getImage(networkDispatcher: CoroutineDispatcher, iconUrl: String): Bitmap? {
    return withContext(networkDispatcher) {
        try {
            Glide.with(this@getImage)
                .asBitmap()
                .load(iconUrl)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                .submit()
                .get()
        } catch (e: ExecutionException) {
            Log.e("TAG", "getAppIcon: ${e.message}")
            null
        } catch (e: SocketTimeoutException) {
            Log.e("TAG", "getAppIcon: ${e.message}")
            null
        }
    }
}