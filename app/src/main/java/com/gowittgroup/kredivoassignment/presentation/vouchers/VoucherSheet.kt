package com.gowittgroup.kredivoassignment.presentation.vouchers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gowittgroup.kredivoassignment.R
import com.gowittgroup.kredivoassignment.domain.models.Voucher
import com.gowittgroup.kredivoassignment.presentation.components.KredivoFilledButton
import com.gowittgroup.kredivoassignment.ui.theme.Dimens
import com.gowittgroup.kredivoassignment.ui.theme.KredivoAssignmentTheme

@Composable
fun VoucherSheet(
    modifier: Modifier = Modifier,
    vouchers: List<Voucher> = listOf(),
    onVoucherSelected: (Voucher) -> Unit = {}
) {
    LazyColumn(modifier = modifier) {
        items(items = vouchers, key = { it.voucherCode }) { voucher ->
            VoucherItem(voucher, onVoucherSelected)
        }
    }
}

@Composable
fun VoucherItem(voucher: Voucher, onVoucherSelected: (Voucher) -> Unit) {
    Card(
        modifier = Modifier.padding(Dimens.spacingNormal),
        colors = CardDefaults
            .cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column {
            AsyncImage(
                model = voucher.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Row(modifier = Modifier.padding(Dimens.spacingNormal)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${voucher.name} ${voucher.maxDiscount}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Valid until ${voucher.endDate}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }

                KredivoFilledButton(
                    text = stringResource(R.string.use),
                    modifier = Modifier.height(36.dp),
                    onClick = { onVoucherSelected(voucher) }
                )
            }
        }

    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TransactionsScreenScreenPrev() {
    KredivoAssignmentTheme {
        VoucherSheet(
            vouchers = listOf(
                Voucher.DEFAULT.copy(
                    voucherCode = "3fs423",
                    endDate = "4th May 2025",
                    name = "Discount 75%",
                    maxDiscount = "100",
                    imageUrl = "https://placehold.co/1000x400/239CEC/FFFFFF/png"
                ),
                Voucher.DEFAULT.copy(
                    voucherCode = "3fs42",
                    endDate = "4th May 2025",
                    name = "Discount 75%",
                    maxDiscount = "100",
                    imageUrl = "https://placehold.co/1000x400/239CEC/FFFFFF/png"
                ),
                Voucher.DEFAULT.copy(
                    voucherCode = "3s423",
                    endDate = "4th May 2025",
                    name = "Discount 75%",
                    maxDiscount = "100",
                    imageUrl = "https://placehold.co/1000x400/239CEC/FFFFFF/png"
                ),
                Voucher.DEFAULT.copy(
                    voucherCode = "3f423",
                    endDate = "4th May 2025",
                    name = "Discount 75%",
                    maxDiscount = "100",
                    imageUrl = "https://placehold.co/1000x400/239CEC/FFFFFF/png"
                )


            )
        )
    }
}