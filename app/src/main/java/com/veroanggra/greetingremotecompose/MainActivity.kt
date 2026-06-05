package com.veroanggra.greetingremotecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.veroanggra.greetingremotecompose.remote.greeting_card.CardViewModel
import com.veroanggra.greetingremotecompose.remote.greeting_card.RemoteWelcomeCardLoader
import com.veroanggra.greetingremotecompose.remote.greeting_card.uploadRemoteLayout
import com.veroanggra.greetingremotecompose.ui.theme.GreetingRemoteComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingRemoteComposeTheme {
                val cardViewModel: CardViewModel = viewModel()
                val context = LocalContext.current
                val scope = rememberCoroutineScope()
                val isAdmin = false
                var isRefreshing by remember { mutableStateOf(false) }

                LaunchedEffect(Unit) {
                    try {
                        if (isAdmin) {
                            uploadRemoteLayout(context) {
                                cardViewModel.fetchRemoteBlueprint()
                            }
                        } else {
                            cardViewModel.fetchRemoteBlueprint()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets(0, 0, 0, 0))
                ) {
                    PullToRefreshBox(
                        isRefreshing = isRefreshing,
                        onRefresh = {
                            scope.launch {
                                isRefreshing = true
                                try {
                                    cardViewModel.fetchRemoteBlueprint()
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                } finally {
                                    isRefreshing = false
                                }
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            item {
                                Box(
                                    modifier = Modifier.fillParentMaxSize()
                                ) {
                                    RemoteWelcomeCardLoader(viewModel = cardViewModel)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
