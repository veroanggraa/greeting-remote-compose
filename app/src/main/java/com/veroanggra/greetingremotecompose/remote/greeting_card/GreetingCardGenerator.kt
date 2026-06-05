package com.veroanggra.greetingremotecompose.remote.greeting_card

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.remote.creation.compose.capture.captureSingleRemoteDocument
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.google.firebase.storage.FirebaseStorage
import com.veroanggra.greetingremotecompose.remote.custom_card.RemoteIedCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("RestrictedApi")
suspend fun uploadRemoteLayout(context: Context, onSuccess: () -> Unit) {
//    val imageUrl = "https://i.ibb.co.com/kVj0YW7b/5efd52f50c23495e3f64c7805d749dbb-1.png"
    val imageUrl= "https://i.ibb.co.com/CpwPqDPH/559b170709688923291a04e22b741ef3-1.png"

    val bitmap: Bitmap? = withContext(Dispatchers.IO) {
        try {
            val imageLoader = ImageLoader(context)

            val request = ImageRequest.Builder(context)
                .data(imageUrl)
                .allowHardware(false)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .build()

            val result = imageLoader.execute(request)

            if (result is SuccessResult) {
                (result.drawable as BitmapDrawable).bitmap
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    val remoteDocument = captureSingleRemoteDocument(context = context) {
        RemoteIedCard(bitmap = bitmap)
    }
    val binaryData = remoteDocument.bytes

    val storageRef = FirebaseStorage.getInstance().reference.child("layouts/main_screen.bin")
    storageRef.putBytes(binaryData)
        .addOnSuccessListener {
            println("Layout uploaded!")
            onSuccess()
        }
        .addOnFailureListener { println("Upload failed: ${it.message}") }
}

