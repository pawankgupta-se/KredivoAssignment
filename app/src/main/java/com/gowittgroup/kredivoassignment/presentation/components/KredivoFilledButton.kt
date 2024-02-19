package com.gowittgroup.kredivoassignment.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gowittgroup.kredivoassignment.ui.theme.ColorSecondary

@Composable
fun KredivoFilledButton(
    modifier: Modifier = Modifier,
    text: String = "",
    contentColor: Color = Color.White,
    containerColor: Color = MaterialTheme.colorScheme.secondary,
    onClick: () -> Unit = {},
    cornerRadius: Dp = 8.dp
) {
    Button(
        modifier = modifier.height(48.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,

            ),
        shape = RoundedCornerShape(cornerRadius),
    ) {
        Text(text = text)

    }
}
