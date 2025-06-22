package com.guru.composecookbook.tiktok.components.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.R
import com.guru.composecookbook.data.model.Album
import com.guru.composecookbook.theme.tiktokRed

@Composable
fun ProfileTopSection(album: Album) {
  Spacer(modifier = Modifier.height(16.dp))
  Image(
    painter = painterResource(id = album.imageId),
    contentDescription = null,
    modifier = Modifier.size(100.dp).clip(CircleShape)
  )
  Text(
    text = "@${album.artist.trim()}",
    style = MaterialTheme.typography.h6,
    modifier = Modifier.padding(8.dp)
  )
  Row(
    modifier = Modifier.fillMaxWidth().padding(12.dp),
    horizontalArrangement = Arrangement.Center
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.padding(horizontal = 24.dp)
    ) {
      Text(text = "15", style = MaterialTheme.typography.h6)
      Text(text = "Following", style = MaterialTheme.typography.subtitle2)
    }
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.padding(horizontal = 24.dp)
    ) {
      Text(text = "157k", style = MaterialTheme.typography.h6)
      Text(text = "Followers", style = MaterialTheme.typography.subtitle2)
    }
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.padding(horizontal = 32.dp)
    ) {
      Text(text = "1.78M", style = MaterialTheme.typography.h6)
      Text(text = "Likes", style = MaterialTheme.typography.subtitle2)
    }
  }
  Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
    Button(
      onClick = {},
      colors = ButtonDefaults.buttonColors(backgroundColor = tiktokRed, contentColor = Color.White),
      modifier = Modifier.height(50.dp).padding(horizontal = 2.dp)
    ) {
      Text(text = "Follow", modifier = Modifier.padding(horizontal = 32.dp))
    }
    Icon(
      painter = painterResource(id = R.drawable.ic_instagram),
      contentDescription = null,
      modifier =
        Modifier.height(50.dp)
          .width(50.dp)
          .padding(2.dp)
          .border(
            border = BorderStroke(1.dp, color = MaterialTheme.colors.onSurface),
            shape = RoundedCornerShape(4.dp)
          )
    )
    Icon(
      imageVector = Icons.Default.ArrowDropDown,
      contentDescription = null,
      modifier =
        Modifier.height(50.dp)
          .width(50.dp)
          .padding(2.dp)
          .border(
            border = BorderStroke(1.dp, color = MaterialTheme.colors.onSurface),
            shape = RoundedCornerShape(4.dp)
          )
    )
  }
  Text(
    text = "My Songs and albums- ${album.song}, ${album.descriptions}: Stay Tuned",
    modifier = Modifier.padding(vertical = 8.dp, horizontal = 32.dp),
    style = MaterialTheme.typography.body2,
    textAlign = TextAlign.Center
  )
  ProfileTabs()
}
