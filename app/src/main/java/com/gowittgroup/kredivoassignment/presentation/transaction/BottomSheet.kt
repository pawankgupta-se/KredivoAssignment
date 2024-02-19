package com.gowittgroup.kredivoassignment.presentation.transaction

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import com.gowittgroup.kredivoassignment.domain.models.Voucher
import com.gowittgroup.kredivoassignment.presentation.vouchers.VoucherSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onDismissRequest: () -> Unit,
    state: SheetState,
    onVoucherSelected: (Voucher) -> Unit,
    vouchers: List<Voucher>
) {
    ModalBottomSheet(
        sheetState = state,
        onDismissRequest = { onDismissRequest() }) {
        VoucherSheet(
            onVoucherSelected = onVoucherSelected,
            vouchers = vouchers
        )
    }
}