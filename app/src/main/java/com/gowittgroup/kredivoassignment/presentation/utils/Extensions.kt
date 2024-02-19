package com.gowittgroup.kredivoassignment.presentation.utils

fun String.toDoubleOrDefault(default: Double = 0.0): Double =
    this.toDoubleOrNull() ?: default
