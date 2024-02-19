package com.gowittgroup.kredivoassignment.presentation.topup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gowittgroup.kredivoassignment.domain.models.Product
import com.gowittgroup.kredivoassignment.domain.models.Response
import com.gowittgroup.kredivoassignment.domain.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TopUpUiState(
    val mobileNumber: String = "",
    val isdCode: String = "+62",
    val products: List<Product> = mutableListOf(),
    val errorMessage: String = ""
)

sealed class TopUpEvent {
    data class OnMobileNumberChange(val mobileNumber: String) : TopUpEvent()
    data object OnTopUpSelected : TopUpEvent()
}

@HiltViewModel
class TopUpViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(TopUpUiState())
    val uiState = _uiState.asStateFlow()

    private fun onMobileNumberChange(number: String) {
        clearErrorMessage()
        _uiState.update { it.copy(mobileNumber = number) }
    }

    init {
        viewModelScope.launch {
            when (val res = getProductsUseCase()) {
                is Response.Success -> {
                    _uiState.update { it.copy(products = res.data) }
                    clearErrorMessage()
                }

                is Response.Error -> _uiState.update {
                    it.copy(errorMessage = res.msg)
                }
            }
        }
    }

    fun handleTopUpEvent(topUpEvent: TopUpEvent) {

        when (topUpEvent) {
            is TopUpEvent.OnMobileNumberChange -> {

                onMobileNumberChange(topUpEvent.mobileNumber)
            }

            TopUpEvent.OnTopUpSelected -> if (uiState.value.mobileNumber.isBlank()) {
                _uiState.update { it.copy(errorMessage = "Please enter mobile number.") }
            } else {
                clearErrorMessage()
            }
        }
    }

    private fun clearErrorMessage() {
        _uiState.update { it.copy(errorMessage = "") }
    }
}