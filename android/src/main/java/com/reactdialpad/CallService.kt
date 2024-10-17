package com.reactdialpad

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.telecom.Call
import android.telecom.InCallService
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.reactdialpad.callManager.CallActivity
import com.reactdialpad.callManager.CallRepository

class CallService: InCallService() {
  @SuppressLint("MissingPermission")
  override fun onCallAdded(call: Call?) {
    super.onCallAdded(call)
    call?.let {
      CallRepository.setCall(it)
      val callerNumber = it.details.handle?.schemeSpecificPart // Extract the caller number
      showIncomingCallNotification(callerNumber)
    }
    //OngoingCall().setCall(call)
    //CallActivity.start(this, call)
  }

  override fun onCallRemoved(call: Call?) {
    super.onCallRemoved(call)
    CallRepository.clearCall()
    //OngoingCall().setCall(null)
  }

  @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
  private fun showIncomingCallNotification(callerNumber: String?) {

    val intent = Intent(this, CallActivity::class.java).apply {
      putExtra("caller_number", callerNumber) // Pass the caller number to the activity
      flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
    }
    val pendingIntent = PendingIntent.getActivity(
      this,
      0,
      intent,
      PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    // Create the notification channel (if not already created)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val notificationManager = getSystemService(NotificationManager::class.java)
      if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
        val channel = NotificationChannel(
          CHANNEL_ID,
          CHANNEL_NAME,
          NotificationManager.IMPORTANCE_HIGH
        )
        val ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        channel.setSound(
          ringtoneUri,
          AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        )
          notificationManager.createNotificationChannel(channel)
      }
    }

    val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
      .setSmallIcon(com.facebook.react.R.drawable.ic_resume) // Replace with your app's call icon
      .setContentTitle("Incoming Call")
      .setContentText("Call from: $callerNumber")
      .setPriority(NotificationCompat.PRIORITY_HIGH)
      .setCategory(NotificationCompat.CATEGORY_CALL)
      .setOngoing(true) // Keeps the notification persistent
      .setAutoCancel(true) // Remove the notification when the user taps on it
      .setContentIntent(pendingIntent) // Tapping on the notification will open the full-screen activity
      .setFullScreenIntent(pendingIntent, true) // Full-screen intent to show the activity immediately


    val notificationManager = NotificationManagerCompat.from(this)
    notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
  }
  companion object {
    private const val CHANNEL_ID = "notification_channel"
    private const val CHANNEL_NAME = "Notification Channel"
    private const val NOTIFICATION_ID = 1
  }
}
