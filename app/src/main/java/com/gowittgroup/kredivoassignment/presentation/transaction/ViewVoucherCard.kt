package com.gowittgroup.kredivoassignment.presentation.transaction

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.unit.dp
import com.gowittgroup.kredivoassignment.R
import com.gowittgroup.kredivoassignment.presentation.components.KredivoFilledButton
import com.gowittgroup.kredivoassignment.ui.theme.Dimens
import com.gowittgroup.kredivoassignment.ui.theme.ColorLightGray
import com.gowittgroup.kredivoassignment.ui.theme.ColorLightOrange

@Composable
 fun ViewVoucherCard(showVouchers: (Boolean) -> Unit) {
    Card(
        border = BorderStroke(1.dp, ColorLightGray),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(Dimens.spacingNormal)
        ) {
            // TODO: need to change icon
            Icon(
                painter = painterResource(id = R.drawable.ic_offer),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(Dimens.spacingMedium))
            Text(text = "Voucher for you", modifier = Modifier.weight(1f))
            KredivoFilledButton(
                modifier = Modifier.height(36.dp),
                text = "View",
                containerColor = ColorLightOrange,
                contentColor = MaterialTheme.colorScheme.secondary,
                onClick = {
                    showVouchers(true)
                }

            )
        }
    }
}