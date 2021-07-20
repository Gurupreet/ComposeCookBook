package com.guru.composecookbook.ui.home.motionlayout

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.layoutId
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.moviesapp.ui.home.components.imageIds
import com.guru.fontawesomecomposelib.FaIcon

@Preview(group = "motion7")
@Composable
fun MotionLayoutDemo() {
    Column(Modifier.background(Color.White)) {
        ButtonsMotionExample()
        Spacer(modifier = Modifier.height(200.dp))
        ImageMotionExample()
    }
}

@Composable
private fun ButtonsMotionExample() {
    var animateButton by remember { mutableStateOf(false) }
    val buttonAnimationProgress by animateFloatAsState(
        targetValue = if (animateButton) 1f else 0f,
        animationSpec = tween(1000)
    )
    Spacer(modifier = Modifier.height(16.dp))
    MotionLayout(
        ConstraintSet(
            """ {
                button1: { 
                         width: "spread",
                height: 60,
                start: ['parent', 'start', 16],
                end: ['parent', 'end', 16],
                top: ['parent', 'top', 16]
                },
                button2: { 
                         width: "spread",
                height: 60,
                start: ['parent', 'start', 16],
                end: ['parent', 'end', 16],
                top: ['button1', 'bottom', 16]
                },
                     button3: { 
                         width: "spread",
                height: 60,
                start: ['parent', 'start', 16],
                end: ['parent', 'end', 16],
                top: ['button2', 'bottom', 16]
                }
            } """
        ),
        ConstraintSet(
            """ {
                    button1: { 
                width: 100,
                height: 60,
                start: ['parent', 'start', 16],
                end: ['button2', 'start', 16]
                },
                      button2: { 
                width: 100,
                height: 60,
                start: ['button1', 'end', 16],
                end: ['button2', 'start', 16]
                },
                         button3: { 
                width: 100,
                height: 60,
                start: ['button2', 'end', 16],
                end: ['parent', 'end', 16]
                }
            } """
        ),
        progress = buttonAnimationProgress,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
    ) {
        Button(
            onClick = { animateButton = !animateButton }, modifier = Modifier.layoutId
                ("button1")
        ) {
            Text(text = "Button1")
        }
        Button(
            onClick = { animateButton = !animateButton }, modifier = Modifier.layoutId
                ("button2")
        ) {
            Text(text = "Button2")
        }
        Button(
            onClick = { animateButton = !animateButton }, modifier = Modifier.layoutId
                ("button3")
        ) {
            Text(text = "Button3")
        }
    }
}

@Composable
private fun ImageMotionExample() {
    val albums = AlbumsDataProvider.albums.take(4)
    var animateImage by remember { mutableStateOf(false) }
    val imageAnimationProgress by animateFloatAsState(
        targetValue = if (animateImage) 1f else 0f,
        animationSpec = tween(1000)
    )
    MotionLayout(
        ConstraintSet(
            """ {
                    image1: { 
                width: 150,
                height: 150,
                start: ['parent', 'start', 16]
                },
                      image2: { 
                width: 150,
                height: 150,
                start: ['parent', 'start', 24]
                },
                         image3: { 
                width: 150,
                height: 150,
                start: ['parent', 'start', 32]
                },
                           image4: { 
                width: 150,
                height: 150,
                start: ['parent', 'start', 40]
                }
            } """
        ),
        ConstraintSet(
            """ {
                    image1: { 
                width: 150,
                height: 150,
                start: ['parent', 'start', 16]
                },
                      image2: { 
                width: 150,
                height: 150,
                start: ['image1', 'end', 16]
                },
                         image3: { 
                width: 150,
                height: 150,
                start: ['parent', 'start', 16],
                top: ['image1', 'bottom', 16]
                },
                             image4: { 
                width: 150,
                height: 150,
                start: ['image1', 'end', 16],
                top: ['image1', 'bottom', 16]
                }
            } """
        ),
        progress = imageAnimationProgress,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(painter = painterResource(id = albums[0].imageId), contentDescription = "",
            modifier = Modifier
                .layoutId("image1")
                .clickable { animateImage = !animateImage })
        Image(painter = painterResource(id = albums[1].imageId), contentDescription = "",
            modifier = Modifier
                .layoutId("image2")
                .clickable { animateImage = !animateImage })
        Image(painter = painterResource(id = albums[2].imageId), contentDescription = "",
            modifier = Modifier
                .layoutId("image3")
                .clickable { animateImage = !animateImage })
        Image(painter = painterResource(id = albums[3].imageId), contentDescription = "",
            modifier = Modifier
                .layoutId("image4")
                .clickable { animateImage = !animateImage })
    }
}