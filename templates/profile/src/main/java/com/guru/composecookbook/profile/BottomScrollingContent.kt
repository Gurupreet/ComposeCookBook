package com.guru.composecookbook.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.typography

@ExperimentalMaterialApi
@Composable
fun BottomScrollingContent() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .padding(8.dp)
    ) {
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
        MoreInfoSection()
    }
}
