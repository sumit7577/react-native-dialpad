package com.reactdialpad.callManager

import android.telecom.Call


object CallRepository {
  private var currentCall: Call? = null

  // Set the current call
  fun setCall(call: Call?) {
    currentCall = call
  }

  // Get the current call
  fun getCall(): Call? {
    return currentCall
  }

  // Clear the call when it's ended or no longer needed
  fun clearCall() {
    currentCall = null
  }
}
