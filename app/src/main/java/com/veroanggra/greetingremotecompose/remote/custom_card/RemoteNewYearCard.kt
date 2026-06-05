package com.veroanggra.greetingremotecompose.remote.custom_card

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import androidx.compose.remote.creation.compose.layout.RemoteArrangement
import androidx.compose.remote.creation.compose.layout.RemoteBox
import androidx.compose.remote.creation.compose.layout.RemoteColumn
import androidx.compose.remote.creation.compose.layout.RemoteImage
import androidx.compose.remote.creation.compose.layout.RemoteSpacer
import androidx.compose.remote.creation.compose.layout.RemoteText
import androidx.compose.remote.creation.compose.modifier.RemoteModifier
import androidx.compose.remote.creation.compose.modifier.fillMaxSize
import androidx.compose.remote.creation.compose.modifier.fillMaxWidth
import androidx.compose.remote.creation.compose.modifier.height
import androidx.compose.remote.creation.compose.state.RemoteColor
import androidx.compose.remote.creation.compose.state.RemoteDp
import androidx.compose.remote.creation.compose.state.rsp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
@SuppressLint("RestrictedApi")
fun RemoteNewYearCard(bitmap: Bitmap?) {
    RemoteBox(modifier = RemoteModifier.fillMaxSize()) {
        bitmap?.asImageBitmap()?.let { imageBitmap ->
            RemoteImage(
                bitmap = imageBitmap,
                contentDescription = null,
                modifier = RemoteModifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        RemoteColumn(
            modifier = RemoteModifier.fillMaxSize(),
            verticalArrangement = RemoteArrangement.Center
        ) {
            RemoteSpacer(modifier = RemoteModifier.height(RemoteDp(250.dp)))
            RemoteText(
                modifier = RemoteModifier.fillMaxWidth(),
                text = "Happy New Year",
                color = RemoteColor(Color.WHITE), fontSize = 35.rsp, textAlign = TextAlign.Center
            )
            RemoteText(
                modifier = RemoteModifier.fillMaxWidth(),
                text = "2026",
                color = RemoteColor(Color.WHITE), fontSize = 55.rsp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold
            )
        }
    }
}