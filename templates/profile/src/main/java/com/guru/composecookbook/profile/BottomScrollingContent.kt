package com.guru.composecookbook.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.typography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomScrollingContent() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp)
    ) {
        SocialRow()
        Text(
            text = "About Me",
            style = typography.h6,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 8.dp, top = 12.dp)
        )
        Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
        Text(
            text = stringResource(id = R.string.about_me),
            style = typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
        InterestsSection()
        MyPhotosSection()
        Text(
            text = "About Project",
            style = typography.h6,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 8.dp, top = 16.dp)
        )
        Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
        Text(
            text = stringResource(id = R.string.about_project),
            style = typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
        MoreInfoSection()
    }
}
