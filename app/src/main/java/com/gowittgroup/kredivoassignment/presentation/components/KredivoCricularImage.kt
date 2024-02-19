package com.gowittgroup.kredivoassignment.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.gowittgroup.kredivoassignment.ui.theme.Dimens

@Composable
fun KredivoCircularImage(@DrawableRes id: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        modifier = modifier
            .size(Dimens.iconSizeMedium)
            .border(Dimens.strokeThin, MaterialTheme.colorScheme.primary, CircleShape)
            .clip(shape = CircleShape),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun KredivoCircularImage(url: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier
            .size(Dimens.iconSizeMedium)
            .border(Dimens.strokeThin, MaterialTheme.colorScheme.primary, CircleShape)
            .clip(shape = CircleShape),
        contentScale = ContentScale.Fit
    )
}