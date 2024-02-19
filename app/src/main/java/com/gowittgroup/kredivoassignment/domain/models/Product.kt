package com.gowittgroup.kredivoassignment.domain.models


data class Product(
    val billType: String,
    val description: String,
    val label: String,
    val nominal: String,
    val telecomOperator: String,
    val price: String,
    val productCode: String,
    val sequence: Int
){
    companion object{
       val DEFAULT = Product(
            billType = "",
            description = "",
            label = "",
            nominal = "",
            telecomOperator = "",
            price = "",
            productCode = "",
            sequence = 0
        )
    }
}