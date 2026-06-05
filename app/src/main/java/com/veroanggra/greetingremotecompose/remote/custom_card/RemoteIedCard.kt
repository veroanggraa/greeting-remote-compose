package com.veroanggra.greetingremotecompose.remote.custom_card

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import androidx.compose.remote.creation.compose.action.Action
import androidx.compose.remote.creation.compose.layout.RemoteAlignment
import androidx.compose.remote.creation.compose.layout.RemoteArrangement
import androidx.compose.remote.creation.compose.layout.RemoteBox
import androidx.compose.remote.creation.compose.layout.RemoteColumn
import androidx.compose.remote.creation.compose.layout.RemoteImage
import androidx.compose.remote.creation.compose.layout.RemoteRow
import androidx.compose.remote.creation.compose.layout.RemoteSpacer
import androidx.compose.remote.creation.compose.layout.RemoteText
import androidx.compose.remote.creation.compose.modifier.RemoteModifier
import androidx.compose.remote.creation.compose.modifier.background
import androidx.compose.remote.creation.compose.modifier.clickable
import androidx.compose.remote.creation.compose.modifier.clip
import androidx.compose.remote.creation.compose.modifier.fillMaxSize
import androidx.compose.remote.creation.compose.modifier.fillMaxWidth
import androidx.compose.remote.creation.compose.modifier.height
import androidx.compose.remote.creation.compose.modifier.padding
import androidx.compose.remote.creation.compose.modifier.wrapContentSize
import androidx.compose.remote.creation.compose.shapes.RemoteRoundedCornerShape
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
fun RemoteIedCard(bitmap: Bitmap?) {
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
            horizontalAlignment = RemoteAlignment.CenterHorizontally
        ) {
            RemoteSpacer(modifier = RemoteModifier.height(RemoteDp(300.dp)))
            RemoteText(
                modifier = RemoteModifier.fillMaxWidth(),
                text = "Happy Eid al-Fitr",
                color = RemoteColor(Color.LTGRAY), fontSize = 40.rsp, textAlign = TextAlign.Center
            )
            RemoteSpacer(modifier = RemoteModifier.weight(1f))
            RemoteRow(
                modifier = RemoteModifier
                    .wrapContentSize()
                    .clip(shape = RemoteRoundedCornerShape(RemoteDp(12.dp)))
                    .background(color = RemoteColor(0xFFFDD2C1.toInt()))
                    .clickable(action = Action.Empty, enabled = true),
                verticalAlignment = RemoteAlignment.CenterVertically,
                horizontalArrangement = RemoteArrangement.Center
            ) {
                RemoteText(
                    modifier = RemoteModifier.padding(
                        horizontal = RemoteDp(60.dp),
                        vertical = RemoteDp(12.dp)
                    ),
                    text = "Klaim THR",
                    color = RemoteColor(Color.WHITE),
                    fontSize = 20.rsp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            RemoteSpacer(modifier = RemoteModifier.height(RemoteDp(250.dp)))
        }
    }
}