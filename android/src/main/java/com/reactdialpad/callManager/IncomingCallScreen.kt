package com.reactdialpad.callManager


import android.telecom.Call
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reactdialpad.R

@Composable
fun MainCallHandler(callViewModel: CallViewModel,onReject: () -> Unit){
  val currCallState by callViewModel.callState.collectAsState()
  val currCall by callViewModel.call.collectAsState()
  val callerNumber = currCall.details.handle?.schemeSpecificPart ?: "Unknown"
  if(currCallState == Call.STATE_ACTIVE){
    OnGoingCallScreen(onReject = { onReject()},callerNumber = callerNumber)
  }
  else if(currCallState == Call.STATE_NEW){
    IncomingCallScreen(callerNumber = callerNumber, onAnswer = {callViewModel.answerCall()},
      onReject = { onReject() })
  }
  else{
    Log.d("call State",currCallState.toString())
  }
}

@Composable
fun IncomingCallScreen(
  callerNumber: String,
  onAnswer: () -> Unit,
  onReject: () -> Unit,
  modifier:Modifier = Modifier
) {
  val height = LocalConfiguration.current.screenHeightDp
  val width = LocalConfiguration.current.screenWidthDp
  Box(modifier = modifier.fillMaxSize()){
    Column(modifier = modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center
    ){
      Image(painter = painterResource(R.drawable.image_20241014_125935),
        contentDescription = null,
        modifier = Modifier.width(width.dp)
          .height((height /3.5f).dp),
        contentScale = ContentScale.Fit
      )
    }
    Column(modifier = modifier.fillMaxSize()
      .background(Color(0xFF576DEB).copy(alpha = 0.8f)),
      verticalArrangement = Arrangement.SpaceEvenly
    ) {
      Column(modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
      )
      {
        Text(text=callerNumber,
          fontSize = 28.sp,
          fontFamily = FontFamily.Default,
          fontWeight = FontWeight.Bold,
          color =Color.White
        )
        Text(text = "Calling...",
          fontSize = 14.sp,
          fontFamily = FontFamily.Default,
          fontWeight = FontWeight.Medium,
          color = Color.White
        )
        Box(modifier = modifier
          .clip(CircleShape)
          .background(Color.Gray.copy(alpha = 0.2f))
        ){
          Image(imageVector = Icons.Default.Person,
            contentDescription = "people",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop)
        }

      }

      Row(modifier = modifier.fillMaxWidth()
        .padding(40.dp,0.dp),
        horizontalArrangement = Arrangement.SpaceBetween){
        Box(modifier = modifier
          .clip(CircleShape)
          .background(Color.White)
          .padding(10.dp)
          .clickable{
            onAnswer()
          }
        ){
          Image(painter = painterResource(R.drawable.baseline_call_24),
            contentDescription = null,
            modifier = Modifier.size(30.dp),
            colorFilter =  ColorFilter.tint(Color.Green),
            contentScale = ContentScale.Crop)
        }


        Box(modifier = modifier
          .clip(CircleShape)
          .background(Color.White)
          .padding(10.dp)
          .clickable {
            onReject()
          }
        ){
          Image(painter = painterResource(R.drawable.baseline_call_end_24),
            contentDescription = null,
            modifier = Modifier.size(30.dp),
            colorFilter =  ColorFilter.tint(Color.Red),
            contentScale = ContentScale.Crop)
        }

        Box(modifier = modifier
          .clip(CircleShape)
          .background(Color.White)
          .padding(15.dp)
        ){
          Image(painter = painterResource(R.drawable.baseline_chat_bubble_24),
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            colorFilter =  ColorFilter.tint(Color.Black),
            contentScale = ContentScale.Crop)
        }

      }

    }
  }
}


@Preview(showBackground = true)
@Composable
fun InComingCallScreenPreview(){
  IncomingCallScreen(callerNumber = "+919117517898", onAnswer = {}, onReject = {})
}



