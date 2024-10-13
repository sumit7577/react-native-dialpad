package com.reactdialpad.callManager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun IncomingCallScreen(
  callerNumber: String,
  onAnswer: () -> Unit,
  onReject: () -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(text = "Incoming Call", style = MaterialTheme.typography.bodyLarge)
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = "Caller: $callerNumber", style = MaterialTheme.typography.bodyLarge)
    Spacer(modifier = Modifier.height(24.dp))
    Row {
      Button(
        onClick = onAnswer,
        modifier = Modifier.padding(8.dp)
      ) {
        Text("Answer")
      }
      Button(
        onClick = onReject,
        modifier = Modifier.padding(8.dp)
      ) {
        Text("Reject")
      }
    }
  }
}


@Preview(showBackground = true)
@Composable
fun InComingCallScreenPreview(){
  IncomingCallScreen(callerNumber = "+919117517898", onAnswer = {}, onReject = {})
}

