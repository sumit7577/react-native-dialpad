package com.reactdialpad.callManager

import android.telecom.Call
import android.telecom.VideoProfile
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CallViewModel(private val currCall:Call):ViewModel() {
  private val _call = MutableStateFlow(currCall);
  val call: StateFlow<Call>
    get() = _call

  private val callback = object : Call.Callback() {
    override fun onStateChanged(call: Call, newState: Int) {
      _callState.value = newState
    }
  }

  init {
      call.value.registerCallback(callback)
  }

  private val _callState = MutableStateFlow(Call.STATE_NEW)
  val callState: StateFlow<Int>
    get() = _callState


  fun answerCall() {
    _call.value.answer(VideoProfile.STATE_AUDIO_ONLY)
  }

  fun rejectCall() {
    _call.value.disconnect()
  }

  override fun onCleared() {
    super.onCleared()
    _call.value.unregisterCallback(callback)
  }

}
