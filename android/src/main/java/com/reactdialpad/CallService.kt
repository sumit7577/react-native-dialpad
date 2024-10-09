package com.reactdialpad

import android.telecom.Call
import android.telecom.InCallService
import android.widget.Toast

class CallService: InCallService() {
  override fun onCallAdded(call: Call?) {
    super.onCallAdded(call)
    call?.let {
      val callerNumber = it.details.handle?.schemeSpecificPart // Extract the caller number
      Toast.makeText(this, "Incoming call from: $callerNumber", Toast.LENGTH_LONG).show()
    }
    //OngoingCall().setCall(call)
    //CallActivity.start(this, call)
  }

  override fun onCallRemoved(call: Call?) {
    super.onCallRemoved(call)
    Toast.makeText(this,"Call Ended",Toast.LENGTH_LONG).show()
    //OngoingCall().setCall(null)
  }
}
