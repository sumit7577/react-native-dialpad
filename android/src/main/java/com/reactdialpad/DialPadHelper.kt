package com.reactdialpad

import android.content.Intent
import android.os.Build
import android.telecom.TelecomManager
import android.telecom.TelecomManager.ACTION_CHANGE_DEFAULT_DIALER
import android.telecom.TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod


class DialPadHelper(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext)  {

  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  fun setDefault() {
    val telecomManager = reactApplicationContext.getSystemService(TelecomManager::class.java)
    if (telecomManager != null) {
      val packageName = reactApplicationContext.packageName
      val defaultDialerPackage = telecomManager.defaultDialerPackage

      Toast.makeText(reactApplicationContext, packageName, Toast.LENGTH_LONG).show()

      if (packageName != defaultDialerPackage) {
        val intent = Intent(ACTION_CHANGE_DEFAULT_DIALER)
          .putExtra(EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, packageName)
          .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // Ensure to add this flag
        reactApplicationContext.startActivity(intent)
      } else {
        Toast.makeText(reactApplicationContext, "Already the default dialer", Toast.LENGTH_SHORT).show()
      }
    } else {
      Toast.makeText(reactApplicationContext, "TelecomManager not available", Toast.LENGTH_SHORT).show()
    }
  }

  companion object{
    const val NAME = "DialPadHelper"
  }

}
