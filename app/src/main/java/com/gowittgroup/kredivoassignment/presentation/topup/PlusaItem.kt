package com.gowittgroup.kredivoassignment.presentation.topup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gowittgroup.kredivoassignment.domain.models.Product
import com.gowittgroup.kredivoassignment.ui.theme.Dimens
import com.gowittgroup.kredivoassignment.ui.theme.ColorLightGray

@Composable
fun PlusaItem(product: Product, onItemClick: (Product) -> Unit = {}) {
    Card(
        border = BorderStroke(1.dp, ColorLightGray),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = Modifier
            .padding(Dimens.spacingMedium)
            .aspectRatio(2.0f)
            .fillMaxSize()
            .clickable { onItemClick(product) }
    ) {
        Column(
            modifier = Modifier.padding(Dimens.spacingNormal),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = product.price, style = MaterialTheme.typography.titleLarge)
            Text(text = product.label, color = MaterialTheme.colorScheme.secondary)
        }
    }
}

@Preview
@Composable
fun PlusaItemPrev() {
    PlusaItem(Product.DEFAULT.copy(productCode = "1000", price = "1000"))
}