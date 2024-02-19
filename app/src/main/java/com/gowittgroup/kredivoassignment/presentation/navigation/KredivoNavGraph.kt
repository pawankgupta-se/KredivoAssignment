package com.gowittgroup.kredivoassignment.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gowittgroup.kredivoassignment.presentation.topup.TopUpScreen
import com.gowittgroup.kredivoassignment.presentation.topup.TopUpViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.gowittgroup.kredivoassignment.presentation.Constants.PHONE_NUMBER_ARG_KEY
import com.gowittgroup.kredivoassignment.presentation.Constants.PRODUCT_CODE_ARG_KEY
import com.gowittgroup.kredivoassignment.presentation.paymentdetails.PaymentDetailsScreen
import com.gowittgroup.kredivoassignment.presentation.transaction.TransactionsScreen
import com.gowittgroup.kredivoassignment.presentation.transaction.TransactionsViewModel


@Composable
fun KredivoNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = KredivoNavDestinations.TOP_UP,
) {
    val navigationActions: KredivoNavActions =
        remember { KredivoNavActions(navController = navController) }
    NavHost(navController = navController, startDestination = startDestination) {
        composable(KredivoNavDestinations.TOP_UP) {
            val viewModel = hiltViewModel<TopUpViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            TopUpScreen(
                uiState = uiState,
                modifier = modifier,
                navigateToTransactions = navigationActions.navigateTransactions,
                handleTopUpEvent = viewModel::handleTopUpEvent
            )
        }
        composable(KredivoNavDestinations.TRANSACTIONS,
            arguments = listOf(
                navArgument(PRODUCT_CODE_ARG_KEY) {
                    type = NavType.StringType
                },
                navArgument(PHONE_NUMBER_ARG_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            val viewModel = hiltViewModel<TransactionsViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            TransactionsScreen(
                modifier = modifier,
                uiState = uiState,
                navigateToPaymentDetails = navigationActions.navigatePaymentDetails,
                onTransactionEvent = viewModel::handleTransactionEvent,
                paymentEvent = viewModel.paymentEvent
            )
        }
        composable(KredivoNavDestinations.PAYMENT_DETAILS) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(KredivoNavDestinations.TRANSACTIONS)
            }
            val viewModel = hiltViewModel<TransactionsViewModel>(parentEntry)
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            PaymentDetailsScreen(
                modifier = modifier,
                uiState = uiState,
                onClose = {
                    navController.popBackStack(
                        destinationId = navController.graph.findStartDestination().id,
                        inclusive = false
                    )
                }
            )
        }
    }
}

