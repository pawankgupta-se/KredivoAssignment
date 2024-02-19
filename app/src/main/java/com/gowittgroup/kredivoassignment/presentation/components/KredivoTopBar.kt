package com.gowittgroup.kredivoassignment.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.gowittgroup.kredivoassignment.ui.theme.ColorPrimary
import com.gowittgroup.kredivoassignment.ui.theme.ColorSecondary
import com.gowittgroup.kredivoassignment.ui.theme.KredivoAssignmentTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KredivoTopBar(
    canNavigateBack: Boolean = false,
    showCloseIcon: Boolean = false,
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
    onBackPress: () -> Unit,
    closePaymentFlow:() -> Unit
) {

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = ColorPrimary,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = { onBackPress() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
            if(showCloseIcon){
                IconButton(onClick = { closePaymentFlow() }) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }
        },
        actions = actions
    )
}

@Preview
@Preview
@Composable
fun KredivoTopBarPreview() {
    KredivoAssignmentTheme {
        KredivoTopBar(
            title = "Preview",
            actions = {},
            onBackPress = {},
            closePaymentFlow = {}
        )
    }

}