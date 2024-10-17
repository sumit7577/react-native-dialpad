package com.reactdialpad.callManager

import android.os.Bundle
import android.telecom.Call
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reactdialpad.R

class CallActivity : AppCompatActivity() {
  private lateinit var callViewModel: CallViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      val currentCall = CallRepository.getCall()

      if (currentCall != null) {
        callViewModel = ViewModelProvider(this, CallViewModelFactory(currentCall))[CallViewModel::class.java]
      } else {
        // Handle the case where there is no active call
        finish()
        return
      }
      setContent {
        MaterialTheme {
          MainCallHandler(callViewModel=callViewModel,onReject={
            callViewModel.rejectCall()
            finish()
          })
        }
      }
    }
}


class CallViewModelFactory(private val currCall:Call) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(CallViewModel::class.java)) {
      @Suppress("UNCHECKED_CAST")
      return CallViewModel(currCall) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}
