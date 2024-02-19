package com.gowittgroup.kredivoassignment.presentation.transaction

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gowittgroup.kredivoassignment.R
import com.gowittgroup.kredivoassignment.domain.models.Voucher
import com.gowittgroup.kredivoassignment.presentation.components.KredivoCircularImage
import com.gowittgroup.kredivoassignment.presentation.components.KredivoFilledButton
import com.gowittgroup.kredivoassignment.presentation.components.KredivoTextfield
import com.gowittgroup.kredivoassignment.presentation.components.PaymentCalculationsSection
import com.gowittgroup.kredivoassignment.presentation.utils.toDoubleOrDefault
import com.gowittgroup.kredivoassignment.ui.theme.Dimens
import com.gowittgroup.kredivoassignment.ui.theme.KredivoAssignmentTheme
import com.gowittgroup.kredivoassignment.ui.theme.ColorVeryLightGray
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsScreen(
    uiState: TransactionsUiState,
    modifier: Modifier = Modifier,
    navigateToPaymentDetails: () -> Unit,
    onTransactionEvent: (TransactionEvent) -> Unit,
    paymentEvent: Flow<PaymentEvent>,
) {

    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = context) {
        paymentEvent.collect { event ->
            when (event) {
                is PaymentEvent.done -> {
                    navigateToPaymentDetails()
                }
            }
        }
    }

    Column(modifier = modifier) {
        PurchaseItemSection(uiState.phoneNumber)

        Divider(
            thickness = Dimens.spacingMedium,
            color = ColorVeryLightGray
        )

        PaymentCalculationsSection(
            productLabel = uiState.product.label,
            price = uiState.product.price.toDoubleOrDefault(),
            discount = uiState.discount,
            total = uiState.total,
            subtotal = uiState.subtotal,

            phoneNumber = uiState.phoneNumber
        )

        Divider(
            thickness = Dimens.spacingMedium,
            color = ColorVeryLightGray
        )

        VoucherSection(
            voucher = uiState.voucher,
            discount = uiState.discount,
            removeVoucher = { onTransactionEvent(TransactionEvent.OnVoucherRemoved) },
            showVouchers = { show ->
                showBottomSheet = show
            })

        Divider(
            thickness = Dimens.spacingMedium,
            color = ColorVeryLightGray
        )

        PinSection(
            uiState.pin
        ) { pin -> onTransactionEvent(TransactionEvent.OnPinValueChanged(pin)) }
        Spacer(modifier = Modifier.weight(1f))

        KredivoFilledButton(
            text = stringResource(R.string.pay_button_text),
            onClick = {
                onTransactionEvent(TransactionEvent.OnPayClicked)
            },
            modifier = Modifier
                .fillMaxWidth(),
            cornerRadius = 0.dp
        )




    }


    if (showBottomSheet) {
        BottomSheet(
            state = sheetState,
            onDismissRequest = {
                showBottomSheet = false
            },
            vouchers = uiState.vouchers,
            onVoucherSelected = { voucher ->
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showBottomSheet = false
                        onTransactionEvent(TransactionEvent.OnVoucherApplied(voucher))
                    }
                }
            },

            )
    }

    if (uiState.errorMessage.isNotBlank()) {
        Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
    }
}

@Composable
private fun VoucherSection(
    voucher: Voucher?,
    discount: Double,
    removeVoucher: () -> Unit,
    showVouchers: (Boolean) -> Unit
) {
    Column(modifier = Modifier.padding(Dimens.spacingNormal)) {
        Text(
            text = stringResource(R.string.voucher_label),
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(Dimens.spacingNormal))
        if (voucher != null) {
            AppliedVoucherCard(voucher, discount, removeVoucher)
        } else {
            ViewVoucherCard(showVouchers)
        }

    }
}

@Composable
private fun PinSection(pin: String, onValueChange: (String) -> Unit) {
    var isVisible by remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(Dimens.spacingNormal)) {
        Text(
            text = stringResource(R.string.kredivo_pin),
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(Dimens.spacingNormal))

        KredivoTextfield(
            modifier = Modifier.fillMaxWidth(),
            text = pin,
            label = stringResource(R.string.pin),
            isSecret = !isVisible,
            trailingIcon = {
                Icon(
                    if (isVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        isVisible = !isVisible
                    }
                )
            },
            onValueChange = onValueChange
        )
        Spacer(modifier = Modifier.height(Dimens.spacingNormal))
        ClickableText(text =
        buildAnnotatedString {
            append(stringResource(R.string.by_continuing_i_agree_with))
            append(" ")
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                append("Product and Service Information Summery & Kredivo loan agreement")
            }

        }, onClick = {
        }
        )
    }
}

@Composable
private fun PurchaseItemSection(phoneNumber: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(Dimens.spacingNormal)
    ) {
        KredivoCircularImage(id = R.drawable.log_placeholder)
        Spacer(modifier = Modifier.width(Dimens.spacingMedium))
        Text(text = phoneNumber)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TransactionsScreenScreenPrev() {
    KredivoAssignmentTheme {
        TransactionsScreen(
            uiState = TransactionsUiState(),
            navigateToPaymentDetails = {},
            onTransactionEvent = {},
            paymentEvent = flowOf()
        )
    }
}

