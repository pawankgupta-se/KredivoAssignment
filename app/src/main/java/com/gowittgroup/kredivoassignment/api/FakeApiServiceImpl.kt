package com.gowittgroup.kredivoassignment.api

import com.google.gson.Gson
import com.gowittgroup.kredivoassignment.api.models.ProductsResponse
import com.gowittgroup.kredivoassignment.api.models.RechargeResponse
import com.gowittgroup.kredivoassignment.api.models.VoucherResponse
import javax.inject.Inject

val productResonse = """{
  "status": "OK",
  "message": null,
  "products": [
    {
      "product_code": "Fw22lyO0",
      "bill_type": "mobile",
      "label": "XL Rp 10.000",
      "operator": "xl",
      "nominal": "10000",
      "description": "",
      "sequence": 1,
      "price": "10500"
    },
    {
      "product_code": "Oqkh0cPY",
      "bill_type": "mobile",
      "label": "XL 15,000",
      "operator": "xl",
      "nominal": "15000",
      "description": "",
      "price": "17500"
    },
    {
      "product_code": "0OqckhPY",
      "bill_type": "mobile",
      "label": "XL 20,000",
      "operator": "xl",
      "nominal": "20000",
      "description": "",
      "price": "20000"
    },
    {
      "product_code": "Oq0cw3kh",
      "bill_type": "mobile",
      "label": "XL 40,000",
      "operator": "xl",
      "nominal": "40000",
      "description": "",
      "price": "42500"
    },
    {
      "product_code": "45kd3hPY",
      "bill_type": "mobile",
      "label": "XL 100,000",
      "operator": "xl",
      "nominal": "100000",
      "description": "",
      "price": "101500"
    }
  ]
}"""
val voucherResponse = """{
  "data": [
    {
      "name": "VOUCHER50PROMO",
      "percentage": 500,
      "image_url": "https://placehold.co/1000x400/239CEC/FFFFFF/png",
      "min_transaction_amount": "30000",
      "end_date": "2024-02-03T00:00:00Z",
      "terms_and_condition": "Must meet minimum pulsa amount",
      "how_to_use": "Apply before completing transaction",
      "usage_count": 1,
      "start_date": "2024-02-02T00:00:00Z",
      "max_discount": "20000",
      "voucher_code": "ACBDED88"
    },
    {
      "name": "VOUCHER25PERCENT",
      "percentage": 250,
      "image_url": "https://placehold.co/1000x400/9C23EC/FFFFFF/png",
      "min_transaction_amount": "50000",
      "end_date": "2024-05-02T00:00:00Z",
      "terms_and_condition": "Must meet minimum pulsa amount",
      "how_to_use": "Apply before completing transaction",
      "usage_count": 5,
      "start_date": "2024-01-01T00:00:00Z",
      "max_discount": "15000",
      "voucher_code": "ACBDED89"
    },
    {
      "name": "VOUCHERLUCKYPERCENT",
      "percentage": 88,
      "image_url": "https://placehold.co/1000x400/EC9C23/FFFFFF/png",
      "min_transaction_amount": "0",
      "end_date": "2026-01-01T00:00:00Z",
      "terms_and_condition": "Must meet minimum pulsa amount",
      "how_to_use": "Apply before completing transaction",
      "usage_count": 0,
      "start_date": "2024-01-01T00:00:00Z",
      "max_discount": "10000",
      "voucher_code": "ACBDED87"
    }
  ],
  "status": "OK"
}"""
val rechargeResponse = """{
  "message": "Pastikan pembayaranmu tidak terlambat guna menghindari denda tambahan 1 bulan cicilan. Cek syarat & ketentuan voucher untuk keterangan lebih lengkap.",
  "status": "OK",
  "transaction_context": {
    "transaction_status": "success",
    "merchant_details": {
      "logo_url": "https://placehold.co/400x400/14342B/FFFFFF/png",
      "name": "Kredivo Biller"
    },
    "applied_payment_type": "30_days",
    "checkout_amount": "100000",
    "order_id": "KB-8027de03",
    "item_list": [
      {
        "aggregated_price": "100000",
        "quantity": 1,
        "status": "SETTLED",
        "unit_price": "100000",
        "total_amount": "100000",
        "p_id": 127579536,
        "sku_type": 0,
        "name": "XL 100,000 (+62-08128318238123)",
        "category": "mobile",
        "sku": "dasdqwe2"
      },
      {
        "total_amount": "10000",
        "quantity": 1,
        "sku_type": 1,
        "name": "Admin Fee",
        "sku": "adminfee"
      },
      {
        "total_amount": "-10000",
        "quantity": 1,
        "sku_type": 2,
        "name": "Discount: New Customer",
        "sku": "discount"
      }
    ],
    "expiration_time": "2024-01-15T10:32:01.324Z",
    "amount": "100000",
    "transaction_token": "asd13d23-6035-4a22-be35-nuiasdnianisud3"
  }
}"""

class FakeApiServiceImpl @Inject constructor() : ApiService {
    override fun getProducts(): ProductsResponse {
        return Gson().fromJson(productResonse, ProductsResponse::class.java)
    }

    override fun getVouchers(): VoucherResponse {
        return Gson().fromJson(voucherResponse, VoucherResponse::class.java)
    }

    override fun recharge(): RechargeResponse {
        return Gson().fromJson(rechargeResponse, RechargeResponse::class.java)
    }
}

