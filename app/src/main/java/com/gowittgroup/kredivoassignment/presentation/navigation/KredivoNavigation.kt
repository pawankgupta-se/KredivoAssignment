package com.gowittgroup.kredivoassignment.presentation.navigation

import androidx.navigation.NavController
import com.gowittgroup.kredivoassignment.presentation.Constants


object KredivoNavDestinations {
    const val TOP_UP = "topup"
    val TRANSACTIONS =
        "transactions/{${Constants.PRODUCT_CODE_ARG_KEY}}/{${Constants.PHONE_NUMBER_ARG_KEY}}"
    const val PAYMENT_DETAILS = "paymentdetails"
}

class KredivoNavActions(navController: NavController) {
    val navigateToTopUp: () -> Unit = {
        navController.navigate(KredivoNavDestinations.TOP_UP)
    }
    val navigateTransactions: (productCode: String, mobileNumber: String) -> Unit =
        {

                productCode, mobileNumber ->
            navController.navigate(
                KredivoNavDestinations.TRANSACTIONS.replace(
                    "{${Constants.PRODUCT_CODE_ARG_KEY}}",
                    productCode
                ).replace("{${Constants.PHONE_NUMBER_ARG_KEY}}", mobileNumber)
            )
        }
    val navigatePaymentDetails: () -> Unit = {
        navController.navigate(KredivoNavDestinations.PAYMENT_DETAILS)
    }
}