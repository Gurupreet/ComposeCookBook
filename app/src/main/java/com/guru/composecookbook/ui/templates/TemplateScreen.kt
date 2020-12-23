package com.guru.composecookbook.ui.templates

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp

@Composable
fun TemplateScreen() {
    val context = AmbientContext.current
   LazyColumn {
       items(templates) { template ->
           Button(
               onClick = {
                   context.startActivity(TemplatesActivity.newIntent(context, template, true))
                },
               modifier = Modifier.fillMaxWidth().padding(12.dp)) {
               Text(text = template,  modifier = Modifier.padding(8.dp))
           }
       }
   }

}


val templates = listOf(
    "Logins",
    "Profiles",
    "Settings",
    "On-boarding",
    "Empty Screens",
    "Loaders",
    "Canvas Drawing",
    "Animations",
)