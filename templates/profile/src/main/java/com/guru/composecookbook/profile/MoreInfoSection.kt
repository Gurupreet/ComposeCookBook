package com.guru.composecookbook.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.R

@Composable
fun MoreInfoSection() {
  val context = LocalContext.current
  Text(
    text = "More Info",
    style = MaterialTheme.typography.titleLarge,
    color = MaterialTheme.colorScheme.primary,
    modifier = Modifier.padding(start = 8.dp, top = 16.dp),
  )
  HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
  ListItem(
    leadingContent = {
      Icon(
        painter = painterResource(id = R.drawable.ic_github_square_brands),
        modifier = Modifier.size(24.dp),
        contentDescription = null,
      )
    },
    headlineContent = {
      Text(
        text = "Compose Cookbook github",
        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
      )
    },
    supportingContent = {
      Text(
        text = "Tap to checkout the repo for the project",
        style = MaterialTheme.typography.bodySmall,
      )
    },
    modifier = Modifier.clickable(onClick = { launchSocialActivity(context, "repository") }),
  )
  ListItem(
    leadingContent = { Icon(imageVector = Icons.Rounded.Email, contentDescription = null) },
    headlineContent = {
      Text(
        text = "Contact Me",
        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
      )
    },
    supportingContent = {
      Text(
        text = "Tap to write me about any concern or info at $email",
        style = MaterialTheme.typography.bodySmall,
      )
    },
    modifier = Modifier.clickable(onClick = { launchSocialActivity(context, "repository") }),
  )
  ListItem(
    leadingContent = { Icon(imageVector = Icons.Rounded.Settings, contentDescription = null) },
    headlineContent = {
      Text(
        text = "Demo Settings",
        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
      )
    },
    supportingContent = {
      Text(text = "Not included yet. coming soon..", style = MaterialTheme.typography.bodySmall)
    },
    modifier = Modifier.clickable(onClick = {}),
  )
}
