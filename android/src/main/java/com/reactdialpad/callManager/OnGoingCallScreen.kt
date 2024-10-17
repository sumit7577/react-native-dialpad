package com.reactdialpad.callManager

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reactdialpad.R


class ChatBubbleArrowShape(private val cornerSize: Dp) : Shape {
  override fun createOutline(
    size: Size,
    layoutDirection: LayoutDirection,
    density: Density
  ): Outline {
    // Convert Dp to Px
    val cornerSizePx = with(density) { cornerSize.toPx() }

    // Define the custom path for a circle with an arrow pointing up
    val path = Path().apply {
      // Draw the arrow pointing up
      moveTo(size.width / 2 - cornerSizePx, 0f) // Left side of the arrow
      lineTo(size.width / 2f, -cornerSizePx)    // Tip of the arrow (upwards)
      lineTo(size.width / 2 + cornerSizePx, 0f) // Right side of the arrow

      // Draw the circular shape
      arcTo(
        rect = androidx.compose.ui.geometry.Rect(
          0f, 0f, size.width, size.height
        ),
        startAngleDegrees = 0f,
        sweepAngleDegrees = 360f,
        forceMoveTo = false
      )

      close()
    }

    return Outline.Generic(path)
  }
}



@Composable
fun OnGoingCallScreen(modifier:Modifier = Modifier,
                      onReject: ()-> Unit,
                      callerNumber: String,
                      ) {
  val icons = listOf(
    R.drawable.baseline_dialpad_24,
    R.drawable.baseline_mic_off_24,
    R.drawable.baseline_volume_up_24,
    R.drawable.baseline_add_24
  )
  Column(modifier = modifier.fillMaxSize()
    .background(Color.White),
    verticalArrangement = Arrangement.SpaceBetween) {

    Column(modifier = modifier
      .fillMaxWidth()
      .padding(0.dp, 8.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(12.dp)
    )
    {
      Box(modifier = modifier
        .clip(CircleShape)
        .background(Color.Gray.copy(alpha = 0.2f))){
        Image(imageVector = Icons.Default.Person,
          contentDescription = "people",
          modifier = Modifier.size(80.dp),
          contentScale = ContentScale.Crop)
      }
      Text(text="Calling Via Vodafone Au",
        fontSize = 14.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium
      )
      Text(text = callerNumber,
        fontSize = 30.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold)

    }

    Column(modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp))
      .background(Color.LightGray.copy(alpha = 0.4f))
      .padding(20.dp, 40.dp),
      verticalArrangement = Arrangement.spacedBy(40.dp)
    ){
      Row(modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween){
        icons.forEach{item ->
          Box(modifier = modifier
            .clip(CircleShape)
            .background(Color.White)
            .padding(10.dp)
          ){
            Image(painter = painterResource(item),
              contentDescription = null,
              modifier = Modifier.size(30.dp),
              contentScale = ContentScale.Crop)
          }
        }
      }

      Column(modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally){
        Box(modifier = modifier
          .clip(CircleShape)
          .background(Color.Red)
          .padding(10.dp)
          .clickable { onReject() }
        ){
          Image(painter = painterResource(R.drawable.baseline_call_end_24),
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            colorFilter =  ColorFilter.tint(Color.White),
            contentScale = ContentScale.Crop)
        }
      }
    }

  }
}


@Preview(showBackground = true)
@Composable
fun OnGoingCallScreenPreview(modifier: Modifier = Modifier){
  OnGoingCallScreen(onReject = {}, callerNumber = "+919117517898")

}
