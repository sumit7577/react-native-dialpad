package com.reactdialpad.callManager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.compose.setContent
import com.reactdialpad.R

class CallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      val callerNumber = intent.getStringExtra("caller_number") ?: "Unknown"

      setContent {
        MaterialTheme {
          IncomingCallScreen(callerNumber = callerNumber, onAnswer = {},onReject = {})
        }
      }
    }
}
