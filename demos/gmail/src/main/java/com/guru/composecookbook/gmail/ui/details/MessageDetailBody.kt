package com.guru.composecookbook.gmail.ui.details

import android.webkit.WebView
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.guru.composecookbook.gmail.R
import com.guru.composecookbook.gmail.data.sampleMessage


@Preview
@Composable
fun MessageDetailBody(modifier: Modifier = Modifier) {
    val showExpandedEmailInfo = remember { mutableStateOf(false) }
    val isFavourite = remember { mutableStateOf(false) }
    LazyColumn(modifier = modifier) {

        item {
            Row {
                Text(
                    text = "Sudip Kafle and others share theier thougs on Linkedin",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                        .weight(1f)

                )
                IconButton(onClick = { isFavourite.value = !isFavourite.value }) {
                    Icon(
                        imageVector = if (isFavourite.value) Icons.Outlined.Star else Icons.Outlined.StarBorder,
                        contentDescription = ""
                    )
                }
            }
        }

        item {
            Row {


                Image(
                    painter = painterResource(id = R.drawable.p3),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .padding(start = 8.dp)
                ) {

                    Row {
                        Text(text = "Linkedin")
                        Text(
                            text = "26 Oct",
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .clickable(onClick = {
                                showExpandedEmailInfo.value = !showExpandedEmailInfo.value
                            })
                    )
                    {
                        Text(text = "to me")
                        Icon(imageVector = Icons.Outlined.ExpandMore, contentDescription = null)
                    }
                }

                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Outlined.SubdirectoryArrowLeft,
                        contentDescription = null
                    )
                }
            }
        }

        item {
            if (showExpandedEmailInfo.value) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    Row {
                        Text(text = "From")
                        Text(text = "Linkedin", modifier = Modifier.padding(start = 8.dp))
                        Text(text = "updates-noreply@linkedin.com")
                    }
                    Row {
                        Text(text = "To")
                        Text(text = "Subash Aryal", modifier = Modifier.padding(start = 8.dp))
                        Text(text = "aryal.subash@yahoo.com")
                    }


                    Row {
                        Text(text = "Date")
                        Text(text = "26 Oct 2020,12:17", modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }
        }

        item {
            AndroidView(
                factory = {
                    WebView(it).apply {
                        loadDataWithBaseURL(null, sampleMessage, "text/html", "UTF-8", null)
                    }
                },
                modifier = Modifier.wrapContentHeight()
            )

        }

        item {
            Row {
                ReplyTypeAction(
                    asset = Icons.Outlined.Undo,
                    text = "Reply",
                    modifier = Modifier.weight(1f)
                )
                ReplyTypeAction(
                    asset = Icons.Outlined.Reply,
                    text = "Reply All",
                    modifier = Modifier.weight(1f)
                )
                ReplyTypeAction(
                    asset = Icons.Outlined.Redo,
                    text = "Forward",
                    modifier = Modifier.weight(1f)
                )

            }
        }
    }

}


@Composable
fun ReplyTypeAction(asset: ImageVector, text: String, modifier: Modifier) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        Icon(imageVector = asset, contentDescription = null)
        Text(
            text = text,
            modifier = Modifier
                .padding(start = 8.dp)
        )
    }
}