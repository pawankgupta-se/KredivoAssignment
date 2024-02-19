package com.gowittgroup.kredivoassignment.presentation.topup

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.gowittgroup.kredivoassignment.R
import com.gowittgroup.kredivoassignment.domain.models.Product
import com.gowittgroup.kredivoassignment.presentation.components.KredivoCircularImage
import com.gowittgroup.kredivoassignment.presentation.components.KredivoTextfield
import com.gowittgroup.kredivoassignment.ui.theme.Dimens
import com.gowittgroup.kredivoassignment.ui.theme.KredivoAssignmentTheme
import com.gowittgroup.kredivoassignment.ui.theme.ColorLightGray

@Composable
fun TopUpScreen(
    uiState: TopUpUiState,
    modifier: Modifier = Modifier,
    navigateToTransactions: (productCode: String, mobileNumber: String) -> Unit,
    handleTopUpEvent: (TopUpEvent) -> Unit
) {
    val context = LocalContext.current
    Column(modifier = modifier.padding(vertical = Dimens.spacingNormal)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = Dimens.spacingNormal)
        ) {
            KredivoTextfield(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = uiState.mobileNumber, prefix = {
                    Text(text = uiState.isdCode, color = Color.Gray)
                },
                label = stringResource(R.string.mobile_number),
                trailingIcon = {
                    KredivoCircularImage(
                        id = R.drawable.log_placeholder
                    )
                },
                onValueChange = { mobileNumber ->
                    handleTopUpEvent(
                        TopUpEvent.OnMobileNumberChange(
                            mobileNumber
                        )
                    )
                },
                keyboardType = KeyboardType.Number
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_contacts),
                    contentDescription = "",
                    tint = Color.Gray
                )
            }

        }

        Spacer(modifier = Modifier.height(Dimens.spacingNormal))
        Divider(
            Modifier
                .height(Dimens.spacingMedium)
                .background(ColorLightGray)
        )
        Spacer(modifier = Modifier.height(Dimens.spacingNormal))

        TabScreen(
            product = uiState.products,
            onTupUpSelected = { productCode ->
                handleTopUpEvent(TopUpEvent.OnTopUpSelected)
                if (uiState.mobileNumber.isNotBlank()) {
                    navigateToTransactions(productCode, uiState.mobileNumber)
                }

            }
        )
    }
    if (uiState.errorMessage.isNotBlank()) {
        Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
    }
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
@Composable
fun TopUpScreenPrev() {
    KredivoAssignmentTheme {
        TopUpScreen(
            uiState = TopUpUiState(),
            navigateToTransactions = { _, _ -> },
            handleTopUpEvent = {},
        )
    }
}


@Composable
fun TabScreen(
    product: List<Product>,
    onTupUpSelected: (String) -> Unit,
) {
    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("Pulsa", "Data Package")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    unselectedContentColor = Color.Gray
                )
            }
        }
        when (tabIndex) {
            0 -> PulsaScreen(products = product, onItemClick = { product ->
                onTupUpSelected(product.productCode)
            })

            1 -> DataPackageScreen()
        }
    }
}


