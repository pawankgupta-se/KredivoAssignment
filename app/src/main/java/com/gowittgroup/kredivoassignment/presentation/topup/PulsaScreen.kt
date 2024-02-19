package com.gowittgroup.kredivoassignment.presentation.topup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.gowittgroup.kredivoassignment.domain.models.Product
import com.gowittgroup.kredivoassignment.ui.theme.Dimens

@Composable
fun PulsaScreen(products: List<Product>, onItemClick: (Product) -> Unit) {

    val listState = rememberLazyGridState()

    LazyVerticalGrid(
        state = listState,
        columns = GridCells.Fixed(2), // Change the number of columns here
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.spacingNormal)
    ) {
        items(items = products, key = { it.productCode }) { item ->
            // TODO: Need to update
            PlusaItem(
                product = item,
                onItemClick = onItemClick
            )
        }
    }
}

