package com.veroanggra.greetingremotecompose.remote.greeting_card

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CardViewModel : ViewModel() {
    private val _layoutBytesState = MutableStateFlow<ByteArray?>(null)
    val layoutBytesState: StateFlow<ByteArray?> = _layoutBytesState.asStateFlow()

    fun fetchRemoteBlueprint() {
        val storageRef = Firebase.storage.reference.child("layouts/main_screen.bin")

        storageRef.getBytes(1024 * 1024)
            .addOnSuccessListener { bytes ->
                _layoutBytesState.value = bytes
            }
            .addOnFailureListener { error ->
                Log.e("RemoteCompose", "Failed to stream remote layout: ${error.message}")
            }
    }
}