package com.gowittgroup.kredivoassignment.presentation.paymentdetails

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gowittgroup.kredivoassignment.R
import com.gowittgroup.kredivoassignment.presentation.components.KredivoCircularImage
import com.gowittgroup.kredivoassignment.presentation.components.KredivoFilledButton
import com.gowittgroup.kredivoassignment.presentation.components.OrderDetailsSection
import com.gowittgroup.kredivoassignment.presentation.transaction.TransactionsUiState
import com.gowittgroup.kredivoassignment.presentation.utils.toDoubleOrDefault
import com.gowittgroup.kredivoassignment.ui.theme.ColorGreen

import com.gowittgroup.kredivoassignment.ui.theme.Dimens
import com.gowittgroup.kredivoassignment.ui.theme.KredivoAssignmentTheme
import com.gowittgroup.kredivoassignment.ui.theme.ColorLightGray
import com.gowittgroup.kredivoassignment.ui.theme.ColorVeryLightGray

@Composable
fun PaymentDetailsScreen(
    uiState: TransactionsUiState,
    modifier: Modifier = Modifier,
    onClose: () -> Unit
) {

    Column(modifier = modifier) {
        if (uiState.transactionDetails != null) {
            TransactionStatusSection(
                status = uiState.transactionDetails.transactionStatus,
                orderId = uiState.transactionDetails.orderId
            )

            Divider(
                thickness = Dimens.spacingMedium, color = ColorVeryLightGray
            )

            OrderDetailsSection(
                orderItem = uiState.transactionDetails.orderItemList,
                checkoutAmount = uiState.transactionDetails.checkoutAmount.toDoubleOrDefault(),
                amount = uiState.transactionDetails.amount.toDoubleOrDefault()
            )

            Card(modifier = Modifier.padding(Dimens.spacingNormal)) {
                ClickableText(text = buildAnnotatedString {
                    append(stringResource(R.string.message_for_transaction_fail))
                    append(" ")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(stringResource(R.string.kredivo_support_phone))
                    }
                    append(" ")
                    append("or")
                    append(" ")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(stringResource(R.string.kredivo_support_email))
                    }

                }, onClick = {}, modifier = Modifier.padding(Dimens.spacingNormal)
                )
            }
        }


        Spacer(modifier = Modifier.weight(1f))
        KredivoFilledButton(
            text = stringResource(R.string.ok_button_text),
            onClick = onClose,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 0.dp),
            cornerRadius = 0.dp
        )

        BackHandler(true) {
            onClose()
        }
    }
}

@Composable
private fun TransactionStatusSection(status: String, orderId: String) {
    Column(modifier = Modifier.padding(Dimens.spacingNormal)) {
        Text(
            text = stringResource(R.string.order_details_label),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(Dimens.spacingNormal))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            KredivoCircularImage(id = R.drawable.log_placeholder)
            Spacer(modifier = Modifier.width(Dimens.spacingMedium))
            Text(text = "535434535")
        }
        Spacer(modifier = Modifier.height(Dimens.spacingNormal))
        Divider(thickness = Dimens.strokeThin, color = ColorLightGray)
        Spacer(modifier = Modifier.height(Dimens.spacingNormal))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.status),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = Dimens.spacingNormal)
            )

            Text(text = status, style = MaterialTheme.typography.bodyMedium, color = ColorGreen)
        }
        Spacer(modifier = Modifier.height(Dimens.spacingNormal))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = Dimens.spacingNormal)
        ) {
            Text(
                text = stringResource(R.string.order_id),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = Dimens.spacingNormal)
            )

            Text(text = orderId, style = MaterialTheme.typography.bodyMedium)
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PaymentDetailsScreenPreview() {
    KredivoAssignmentTheme {
        PaymentDetailsScreen(uiState = TransactionsUiState(), onClose = {})
    }
}