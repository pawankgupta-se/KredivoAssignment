package com.gowittgroup.kredivoassignment.presentation.utils

// Can be used local to return currencySymbol
fun currencySymbol(): String {
    return "Rp"
}

// Can be used different format based on currency
fun formatCurrencyValue(value: Double): String{
    return "%.3f".format(value)
}

fun formattedCurrencyWithSymbol(value: Double):String{
    return "${currencySymbol()}${formatCurrencyValue(value)}"
}
