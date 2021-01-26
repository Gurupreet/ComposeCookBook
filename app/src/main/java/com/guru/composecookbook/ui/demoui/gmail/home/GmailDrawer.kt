package com.guru.composecookbook.ui.demoui.gmail.home

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInbox
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview

@Preview
@Composable
fun GmailDrawer(modifier: Modifier = Modifier) {

    ScrollableColumn(modifier = modifier) {

        Text(
            text = "Gmail",
            color = Color.Red,
            fontSize = 24.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
        )

        Divider(thickness = 0.3.dp)
        Spacer(modifier.padding(top = 8.dp))
        DrawerItem(icon = Icons.Filled.AllInbox, title = "All Inbox")
        Spacer(modifier.padding(top = 8.dp))
        Divider(thickness = 0.3.dp)


        Spacer(modifier.padding(top = 8.dp))



        DrawerItem(icon = Icons.Outlined.Inbox, title = "Primary")
        DrawerItem(icon = Icons.Outlined.Groups, title = "Social")
        DrawerItem(icon = Icons.Outlined.LocalOffer, title = "Promotion")

        DrawerCategory(title = "RECENT LABELS")
        DrawerItem(icon = Icons.Outlined.Label, title = "[Imap]/Trash")
        DrawerItem(icon = Icons.Outlined.Label, title = "facebook")

        DrawerCategory(title = "ALL LABELS")
        DrawerItem(icon = Icons.Outlined.StarBorder, title = "Starred")
        DrawerItem(icon = Icons.Outlined.AccessTime, title = "Snoozed")
        DrawerItem(icon = Icons.Outlined.LabelImportant, title = "Important", "99+")
        DrawerItem(icon = Icons.Outlined.Send, title = "Sent", "99+")
        DrawerItem(icon = Icons.Outlined.MoreTime, title = "Scheduled", "99+")
        DrawerItem(icon = Icons.Outlined.MarkunreadMailbox, title = "Outbox", "10")

    }

}

@Composable
fun DrawerItem(icon: ImageVector, title: String, msgCount: String = "") {

    Row {
        Icon(icon, modifier = Modifier.padding(16.dp))
        Text(
            modifier = Modifier.weight(1f)
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp),
            text = title,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            textAlign = TextAlign.Start
        )

        if (msgCount.isNotEmpty()) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically).padding(16.dp),
                text = msgCount,
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Start
            )
        }

    }

}

@Composable
fun DrawerCategory(title: String) {

    Text(
        text = title,
        letterSpacing = 0.7.sp,
        color = MaterialTheme.colors.onBackground,
        fontSize = 12.sp,
        modifier = Modifier.padding(16.dp)
    )

}
