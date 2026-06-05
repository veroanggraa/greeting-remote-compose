package com.veroanggra.greetingremotecompose.remote.greeting_card

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.remote.player.core.RemoteDocument
import androidx.compose.remote.player.view.RemoteComposePlayer

@SuppressLint("RestrictedApi")
@Composable
fun RemoteWelcomeCardLoader(viewModel: CardViewModel = viewModel()) {
    val cardBytes by viewModel.layoutBytesState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets(0, 0, 0, 0)),
        contentAlignment = Alignment.Center
    ) {
        if (cardBytes != null) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    RemoteComposePlayer(context).apply {
                        setOnClickListener {
                            println("Remote visual card dynamic layer clicked!")
                        }
                    }
                },
                update = { viewPlayer ->
                    val document = RemoteDocument(cardBytes!!)
                    viewPlayer.setDocument(document)
                }
            )
        } else {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }
    }
}