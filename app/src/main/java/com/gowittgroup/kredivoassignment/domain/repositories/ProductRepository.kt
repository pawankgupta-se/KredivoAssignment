package com.gowittgroup.kredivoassignment.domain.repositories

import com.gowittgroup.kredivoassignment.domain.models.Product

interface ProductRepository{
     suspend fun getProducts(): List<Product>
}
