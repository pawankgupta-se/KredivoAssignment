package com.gowittgroup.kredivoassignment.data.mappers

import com.gowittgroup.kredivoassignment.api.models.Item
import com.gowittgroup.kredivoassignment.api.models.MerchantDetails
import com.gowittgroup.kredivoassignment.api.models.ProductDto
import com.gowittgroup.kredivoassignment.api.models.TransactionContextDto
import com.gowittgroup.kredivoassignment.api.models.VoucherDto
import com.gowittgroup.kredivoassignment.domain.models.Product
import com.gowittgroup.kredivoassignment.domain.models.OrderItem
import com.gowittgroup.kredivoassignment.domain.models.VendorDetails
import com.gowittgroup.kredivoassignment.domain.models.TransactionDetails
import com.gowittgroup.kredivoassignment.domain.models.Voucher

fun ProductDto.toProduct() = Product(
    billType = billType,
    description = description,
    label = label,
    nominal = nominal,
    telecomOperator = telecomOperator,
    price = price,
    productCode = productCode,
    sequence = sequence
)

fun VoucherDto.toVoucher() = Voucher(
    endDate = endDate,
    howToUse = howToUse,
    imageUrl = imageUrl,
    maxDiscount = maxDiscount,
    minTransactionAmount = minTransactionAmount,
    name = name,
    percentage = percentage,
    startDate = startDate,
    termsAndCondition = termsAndCondition,
    usageCount = usageCount,
    voucherCode = voucherCode
)

fun TransactionContextDto.toTransactionContext() = TransactionDetails(
    amount = amount,
    appliedPaymentType = appliedPaymentType,
    checkoutAmount = checkoutAmount,
    expirationTime = expirationTime,
    orderId = orderId,
    transactionStatus = transactionStatus,
    transactionToken = transactionToken,
    vendorDetails = merchantDetails.toVendorDetails(),
    orderItemList = itemList.map { it.toOrderItem() }
)

fun MerchantDetails.toVendorDetails() = VendorDetails(logoUrl = logoUrl, name = name)
fun Item.toOrderItem() = OrderItem(
    name = name,
    category = category?:"",
    aggregatedPrice = aggregatedPrice?:"",
    pId = pId?:0,
    sku = sku,
    quantity = quantity,
    skuType = skuType,
    status = status?:"",
    totalAmount = totalAmount,
    unitPrice = unitPrice?:""

)