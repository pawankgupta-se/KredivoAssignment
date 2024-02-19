package com.gowittgroup.kredivoassignment.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination

import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gowittgroup.kredivoassignment.R
import com.gowittgroup.kredivoassignment.domain.models.Voucher
import com.gowittgroup.kredivoassignment.presentation.components.KredivoTopBar
import com.gowittgroup.kredivoassignment.presentation.navigation.KredivoNavDestinations
import com.gowittgroup.kredivoassignment.presentation.navigation.KredivoNavGraph
import com.gowittgroup.kredivoassignment.presentation.vouchers.VoucherSheet
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KredivoApp(
    startDestination: String = KredivoNavDestinations.TOP_UP
) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var canNavigateBack by remember { mutableStateOf(false) }
    var showCloseIcon by remember { mutableStateOf(false) }
    navController.addOnDestinationChangedListener { controller, _, _ ->

        canNavigateBack =
            controller.previousBackStackEntry != null && currentRoute != KredivoNavDestinations.PAYMENT_DETAILS
        showCloseIcon =
            navBackStackEntry?.destination?.route == KredivoNavDestinations.PAYMENT_DETAILS
    }


    val title = when (currentRoute) {
        KredivoNavDestinations.TOP_UP -> stringResource(R.string.topup)
        KredivoNavDestinations.TRANSACTIONS -> stringResource(R.string.purchase)
        KredivoNavDestinations.PAYMENT_DETAILS -> stringResource(R.string.payment_details)
        else -> ""
    }

    Scaffold(
        topBar = {
            KredivoTopBar(
                title = title,
                canNavigateBack = canNavigateBack,
                showCloseIcon = showCloseIcon,
                onBackPress = {
                    navController.popBackStack()
                },
                closePaymentFlow = {
                    navController.popBackStack(
                        destinationId = navController.graph.findStartDestination().id,
                        inclusive = false
                    )
                }

            )
        },
        content = { contentPadding ->
            KredivoNavGraph(
                modifier = Modifier.padding(contentPadding),
                navController = navController,
                startDestination = startDestination
            )

        }
    )
}

