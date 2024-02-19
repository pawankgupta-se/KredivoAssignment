package com.gowittgroup.kredivoassignment.presentation.transaction

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gowittgroup.kredivoassignment.R
import com.gowittgroup.kredivoassignment.domain.models.Voucher
import com.gowittgroup.kredivoassignment.presentation.utils.formattedCurrencyWithSymbol
import com.gowittgroup.kredivoassignment.presentation.utils.toDoubleOrDefault
import com.gowittgroup.kredivoassignment.ui.theme.ColorGreen
import com.gowittgroup.kredivoassignment.ui.theme.Dimens

@Composable
 fun AppliedVoucherCard(voucher: Voucher, discount: Double, removeVoucher: () -> Unit) {
    Card(
        border = BorderStroke(1.dp, ColorGreen),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {

        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(Dimens.spacingNormal)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_verified),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(Dimens.spacingMedium))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(
                        R.string.discount_up_to,
                        formattedCurrencyWithSymbol(voucher.maxDiscount.toDoubleOrDefault())
                    ),
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(Dimens.spacingMedium))
                Text(
                    text = stringResource(
                        R.string.yeay_you_got_discount_max,
                        "${voucher.percentage}%",
                        formattedCurrencyWithSymbol(discount)
                    ),
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            // TODO: need to change icon

            Icon(
                painter = painterResource(id = R.drawable.is_remove),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.clickable {
                    removeVoucher()
                })

        }


    }
}