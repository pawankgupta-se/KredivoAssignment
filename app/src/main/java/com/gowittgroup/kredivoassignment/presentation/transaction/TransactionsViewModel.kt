package com.gowittgroup.kredivoassignment.presentation.transaction

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gowittgroup.kredivoassignment.domain.models.Product
import com.gowittgroup.kredivoassignment.domain.models.Response
import com.gowittgroup.kredivoassignment.domain.models.TransactionDetails
import com.gowittgroup.kredivoassignment.domain.models.Voucher
import com.gowittgroup.kredivoassignment.domain.usecases.GetProductsByCodeUseCase
import com.gowittgroup.kredivoassignment.domain.usecases.GetVouchersUseCase
import com.gowittgroup.kredivoassignment.domain.usecases.RechargeUseCase
import com.gowittgroup.kredivoassignment.presentation.Constants.PHONE_NUMBER_ARG_KEY
import com.gowittgroup.kredivoassignment.presentation.Constants.PRODUCT_CODE_ARG_KEY
import com.gowittgroup.kredivoassignment.presentation.utils.toDoubleOrDefault
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class TransactionEvent {
    data class OnPinValueChanged(val pin: String) : TransactionEvent()
    data class OnVoucherApplied(val voucher: Voucher) : TransactionEvent()
    data object OnVoucherRemoved : TransactionEvent()
    data object OnPayClicked : TransactionEvent()
}

data class TransactionsUiState(
    val product: Product = Product.DEFAULT,
    val phoneNumber: String = "",
    val voucher: Voucher? = null,
    val discount: Double = 0.0,
    val total: Double = 0.0,
    val subtotal: Double = 0.0,
    val vouchers: List<Voucher> = mutableListOf(),
    val errorMessage: String = "",
    val pin: String = "",
    val transactionDetails: TransactionDetails? = null
)


@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getProductsByCodeUseCase: GetProductsByCodeUseCase,
    private val getVouchersUseCase: GetVouchersUseCase,
    private val rechargeUseCase: RechargeUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(TransactionsUiState())
    val uiState = _uiState.asStateFlow()

    private val paymentEventChannel = Channel<PaymentEvent>()
    val paymentEvent = paymentEventChannel.receiveAsFlow()

    init {
        val phoneNumber = savedStateHandle.get<String>(PHONE_NUMBER_ARG_KEY)
        phoneNumber?.let { _uiState.update { it.copy(phoneNumber = phoneNumber) } }
        val selectedProductCode = savedStateHandle.get<String>(PRODUCT_CODE_ARG_KEY)
        selectedProductCode?.let { code ->
            viewModelScope.launch {
                when (val res = getProductsByCodeUseCase(code)) {
                    is Response.Success -> {
                        clearErrorMessage()
                        _uiState.update { it.copy(product = res.data) }
                        calculateSubtotal(uiState.value.product.price.toDoubleOrDefault())
                        calculateTotal(uiState.value.subtotal)
                    }

                    is Response.Error -> _uiState.update { it.copy(errorMessage = res.msg) }
                }
            }
        }
        viewModelScope.launch {
            when (val res = getVouchersUseCase()) {
                is Response.Success -> {
                    clearErrorMessage()
                    _uiState.update { it.copy(vouchers = res.data) }
                }

                is Response.Error -> _uiState.update { it.copy(errorMessage = res.msg) }
            }
        }

    }

    private fun calculateSubtotal(price: Double) {
        _uiState.update {
            it.copy(
                subtotal = price
            )
        }
    }

    private fun calculateTotal(subtotal: Double, percentage: Double = 0.0) {
        val discount = subtotal * percentage / 100
        _uiState.update {
            it.copy(
                total = subtotal - discount,
                discount = discount
            )
        }
    }

    private fun useVoucher(voucher: Voucher) {
        calculateTotal(uiState.value.product.price.toDoubleOrDefault(), voucher.percentage.toDouble())
        _uiState.update { it.copy(voucher = voucher) }
    }

    private fun removeVoucher() {
        calculateTotal(uiState.value.product.price.toDoubleOrDefault())
        _uiState.update { it.copy(voucher = null) }
    }

    private fun onPayClick() {
        if (uiState.value.pin.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Please enter pin.") }
        } else {
            viewModelScope.launch {
                when (val res = rechargeUseCase.invoke()) {
                    is Response.Success -> {
                        clearErrorMessage()
                        _uiState.update {
                            it.copy(
                                transactionDetails = res.data
                            )
                        }

                        paymentEventChannel.send(PaymentEvent.done)

                    }

                    is Response.Error -> {
                        _uiState.update {
                            it.copy(
                                errorMessage = res.msg,
                            )
                        }
                        paymentEventChannel.send(PaymentEvent.done)
                    }
                }
            }
        }
    }

    private fun onPinValueChange(value: String) {
        clearErrorMessage()
        _uiState.update { it.copy(pin = value) }
    }

    fun handleTransactionEvent(event: TransactionEvent) {
        when (event) {
            is TransactionEvent.OnPayClicked -> onPayClick()
            is TransactionEvent.OnPinValueChanged -> onPinValueChange(event.pin)
            is TransactionEvent.OnVoucherApplied -> useVoucher(event.voucher)
            is TransactionEvent.OnVoucherRemoved -> removeVoucher()
        }
    }

    private fun clearErrorMessage() {
        _uiState.update { it.copy(errorMessage = "") }
    }
}


sealed class PaymentEvent {
    data object done : PaymentEvent()
}