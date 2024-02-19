package com.gowittgroup.kredivoassignment.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.gowittgroup.kredivoassignment.R
import com.gowittgroup.kredivoassignment.domain.models.OrderItem
import com.gowittgroup.kredivoassignment.presentation.utils.formattedCurrencyWithSymbol
import com.gowittgroup.kredivoassignment.presentation.utils.toDoubleOrDefault
import com.gowittgroup.kredivoassignment.ui.theme.ColorSecondary
import com.gowittgroup.kredivoassignment.ui.theme.Dimens
import com.gowittgroup.kredivoassignment.ui.theme.ColorLightGray

@Composable
fun PaymentCalculationsSection(
    productLabel: String,
    price: Double,
    discount: Double,
    subtotal: Double,
    total: Double,
    phoneNumber: String
) {
    Column(modifier = Modifier.padding(Dimens.spacingNormal)) {
        Text(
            text = stringResource(R.string.payment_details_label),
            style = MaterialTheme.typography.titleMedium
        )
        Row(modifier = Modifier.padding(vertical = Dimens.spacingNormal)) {
            Text(
                text = "${productLabel}($phoneNumber)",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = Dimens.spacingMedium)
            )

            Text(
                text = formattedCurrencyWithSymbol(price),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Divider(thickness = Dimens.strokeThin, color = ColorLightGray)
        Row(modifier = Modifier.padding(vertical = Dimens.spacingNormal)) {
            Text(
                text = stringResource(R.string.subtotal),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = Dimens.spacingMedium)
            )

            Text(
                text = formattedCurrencyWithSymbol(subtotal),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        DashedDivider(strokeWidth = Dimens.strokeThin, color = ColorLightGray)
        if (discount != 0.0) {

            Row(modifier = Modifier.padding(vertical = Dimens.spacingNormal)) {
                Text(
                    text = stringResource(R.string.kredivo_discount),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = Dimens.spacingMedium)
                )

                Text(
                    text = formattedCurrencyWithSymbol(discount),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Divider(thickness = Dimens.strokeThin, color = ColorLightGray)
        }
        Row(modifier = Modifier.padding(vertical = Dimens.spacingNormal)) {
            Text(
                text = stringResource(R.string.pay_in_days, 30),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = Dimens.spacingMedium)
            )

            Text(
                text = formattedCurrencyWithSymbol(total),
                style = MaterialTheme.typography.titleMedium,
                color = ColorSecondary
            )
        }
    }
}

@Composable
fun OrderDetailsSection(
    orderItem: List<OrderItem>,
    amount:Double,
    checkoutAmount: Double,
 ) {
    Column(modifier = Modifier.padding(Dimens.spacingNormal)) {
        Text(
            text = stringResource(R.string.payment_details_label),
            style = MaterialTheme.typography.titleMedium
        )
        val items =  orderItem.filter { it.skuType == 0  }
        items.forEach { item ->
            Row(modifier = Modifier.padding(vertical = Dimens.spacingNormal)) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = Dimens.spacingMedium)
                )

                Text(
                    text = formattedCurrencyWithSymbol(item.totalAmount.toDoubleOrDefault()),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Divider(thickness = Dimens.strokeThin, color = ColorLightGray)
        }

        Row(modifier = Modifier.padding(vertical = Dimens.spacingNormal)) {
            Text(
                text = stringResource(R.string.subtotal),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = Dimens.spacingMedium)
            )

            Text(
                text = formattedCurrencyWithSymbol(amount),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        DashedDivider(strokeWidth = Dimens.strokeThin, color = ColorLightGray)

        Row(modifier = Modifier.padding(vertical = Dimens.spacingNormal)) {
            Text(
                text = stringResource(R.string.pay_in_days, 30),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = Dimens.spacingMedium)
            )

            Text(
                text = formattedCurrencyWithSymbol(checkoutAmount),
                style = MaterialTheme.typography.titleMedium,
                color = ColorSecondary
            )
        }
    }
}


