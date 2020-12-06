package com.guru.composecookbook.ui.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animate
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.gradientBluePurple
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.utils.horizontalGradientBackground

private val initialimageFloat = 170f
private val name = "Gurupreet Singh"
private val email = "gurpreet.usit@gmail.com"
const val twitterUrl = "https://www.twitter.com/_gurupreet"
const val linkedInUrl = "https://www.linkedin.com/in/gurupreet-singh-491a7668/"
const val githubUrl = "https://github.com"
const val githubRepoUrl = "https://github.com/Gurupreet/ComposeCookBook"

//NOTE: This stuff should usually be in a parent activity/Navigator
// We can pass callback to profileScreen to get the click.
private fun launchSocialActivity(context: Context, socialType: String) {
    val intent = when (socialType) {
        "github" -> Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
        "repository" -> Intent(Intent.ACTION_VIEW, Uri.parse(githubRepoUrl))
        "linkedin" -> Intent(Intent.ACTION_VIEW, Uri.parse(linkedInUrl))
        else -> Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl))
    }
    context.startActivity(intent)
}

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .semantics { testTag = "Profile Screen" }
    ) {
        val scrollState = rememberScrollState(0f)
        TopAppBarView(scrollState.value)
        TopBackground()
        ScrollableColumn(scrollState = scrollState, modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(100.dp))
            TopScrollingContent(scrollState)
            BottomScrollingContent()
        }
    }
}

@Composable
fun TopScrollingContent(scrollState: ScrollState) {
    val visibilityChangeFloat = scrollState.value > initialimageFloat - 20
    Row {
        AnimatedImage(scroll = scrollState.value)
        Column(
            modifier = Modifier.padding(start = 8.dp, top = 48.dp)
                .alpha(animate(if (visibilityChangeFloat) 0f else 1f))
        ) {
            Text(
                text = name,
                style = typography.h6.copy(fontSize = 18.sp),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Android developer at Delivery Hero",
                style = typography.subtitle2
            )
        }
    }
}

@Composable
fun BottomScrollingContent() {
    Column(modifier = Modifier.background(MaterialTheme.colors.surface).padding(8.dp)) {
        SocialRow()
        Text(
            text = "About Me",
            style = typography.h6,
            modifier = Modifier.padding(start = 8.dp, top = 12.dp)
        )
        Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
        Text(
            text = stringResource(id = R.string.about_me),
            style = typography.body1,
            modifier = Modifier.fillMaxWidth().padding(8.dp),
        )
        InterestsSection()
        MyPhotosSection()
        Text(
            text = "About Project",
            style = typography.h6,
            modifier = Modifier.padding(start = 8.dp, top = 16.dp)
        )
        Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
        Text(
            text = stringResource(id = R.string.about_project),
            style = typography.body1,
            modifier = Modifier.fillMaxWidth().padding(8.dp),
        )
        MoreInfoSection()
    }
}

@Composable
fun SocialRow() {
    Card(elevation = 8.dp, modifier = Modifier.padding(8.dp)) {
        val context = ContextAmbient.current
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp, vertical = 16.dp)
        ) {
            IconButton(onClick = { launchSocialActivity(context, "github") }) {
                Icon(imageVector = vectorResource(id = R.drawable.ic_github_square_brands))
            }
            IconButton(onClick = { launchSocialActivity(context, "twitter") }) {
                Icon(imageVector = vectorResource(id = R.drawable.ic_twitter_square_brands))
            }
            IconButton(onClick = { launchSocialActivity(context, "linkedin") }) {
                Icon(imageVector = vectorResource(id = R.drawable.ic_linkedin_brands))
            }
        }
    }
}

@Composable
fun MoreInfoSection() {
    val context = ContextAmbient.current
    Text(
        text = "More Info",
        style = typography.h6,
        modifier = Modifier.padding(start = 8.dp, top = 16.dp)
    )
    Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
    ListItem(
        icon = {
            Icon(
                imageVector = vectorResource(id = R.drawable.ic_github_square_brands),
                modifier = Modifier.preferredSize(24.dp)
            )
        },
        text = {
            Text(
                text = "Compose Cookbook github",
                style = typography.body1.copy(fontWeight = FontWeight.Bold)
            )
        },
        secondaryText = { Text(text = "Tap to checkout the repo for the project") },
        modifier = Modifier
            .clickable(onClick = { launchSocialActivity(context, "repository") })
    )
    ListItem(
        icon = { Icon(imageVector = Icons.Rounded.Email) },
        text = {
            Text(
                text = "Contact Me",
                style = typography.body1.copy(fontWeight = FontWeight.Bold)
            )
        },
        secondaryText = { Text(text = "Tap to write me about any concern or info at $email") },
        modifier = Modifier
            .clickable(onClick = { launchSocialActivity(context, "repository") })
    )
    ListItem(
        icon = { Icon(imageVector = Icons.Rounded.Settings) },
        text = {
            Text(
                text = "Demo Settings",
                style = typography.body1.copy(fontWeight = FontWeight.Bold)
            )
        },
        secondaryText = { Text(text = "Not included yet. coming soon..") },
        modifier = Modifier.clickable(onClick = {})
    )
}

@Composable
fun InterestsSection() {
    Text(
        text = "My Interests",
        style = typography.h6,
        modifier = Modifier.padding(start = 8.dp, top = 16.dp)
    )
    Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
    Row(modifier = Modifier.padding(start = 8.dp, top = 8.dp)) {
        InterestTag("Android")
        InterestTag("Compose")
        InterestTag("Flutter")
        InterestTag("SwiftUI")
    }
    Row(modifier = Modifier.padding(start = 8.dp)) {
        InterestTag("Video games")
        InterestTag("Podcasts")
        InterestTag("Basketball")
    }
}

@Composable
fun TopAppBarView(scroll: Float) {
    if (scroll > initialimageFloat + 5) {
        TopAppBar(
            title = {
                Text(text = name)
            },
            navigationIcon = {
                Image(
                    bitmap = imageResource(id = R.drawable.p1),
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                        .preferredSize(32.dp).clip(CircleShape)
                )
            },
            actions = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        )
    }
}


@Composable
fun AnimatedImage(scroll: Float) {
    val dynamicAnimationSizeValue = (initialimageFloat - scroll).coerceIn(36f, initialimageFloat)
    Image(
        bitmap = imageResource(id = R.drawable.p1),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(start = 16.dp)
            .preferredSize(animate(Dp(dynamicAnimationSizeValue)))
            .clip(CircleShape)
    )
}

@Composable
private fun TopBackground() {
    Spacer(
        modifier = Modifier
            .preferredHeight(150.dp)
            .fillMaxWidth()
            .horizontalGradientBackground(gradientBluePurple)
    )
}

@Preview
@Composable
fun ShowProfileScreen() {
    ProfileScreen()
}