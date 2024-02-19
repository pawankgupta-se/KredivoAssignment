package com.gowittgroup.kredivoassignment.data.di

import com.gowittgroup.kredivoassignment.api.ApiService
import com.gowittgroup.kredivoassignment.api.FakeApiServiceImpl
import com.gowittgroup.kredivoassignment.data.datasources.ProductDataSource
import com.gowittgroup.kredivoassignment.data.datasources.ProductDataSourceImpl
import com.gowittgroup.kredivoassignment.data.datasources.RechargeDataSource
import com.gowittgroup.kredivoassignment.data.datasources.RechargeDataSourceImpl
import com.gowittgroup.kredivoassignment.data.datasources.VoucherDataSource
import com.gowittgroup.kredivoassignment.data.datasources.VoucherDataSourceImpl
import com.gowittgroup.kredivoassignment.data.repositories.ProductRepositoryImpl
import com.gowittgroup.kredivoassignment.data.repositories.RechargeRepositoryImpl
import com.gowittgroup.kredivoassignment.data.repositories.VoucherRepositoryImpl
import com.gowittgroup.kredivoassignment.domain.repositories.ProductRepository
import com.gowittgroup.kredivoassignment.domain.repositories.RechargeRepository
import com.gowittgroup.kredivoassignment.domain.repositories.VoucherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindsApiService(apiService: FakeApiServiceImpl): ApiService

    @Binds
    @Singleton
    fun bindsProductDataSource(productDataSource: ProductDataSourceImpl): ProductDataSource

    @Binds
    @Singleton
    fun bindsVoucherDataSource(voucherDataSource: VoucherDataSourceImpl): VoucherDataSource

    @Binds
    @Singleton
    fun bindsRechargeDataSource(rechargeDataSource: RechargeDataSourceImpl): RechargeDataSource

    @Binds
    @Singleton
    fun bindsProductRepository(productRepository: ProductRepositoryImpl): ProductRepository

    @Binds
    @Singleton
    fun bindsVoucherRepository(voucherRepository: VoucherRepositoryImpl): VoucherRepository

    @Binds
    @Singleton
    fun bindsRechargeRepository(rechargeRepository: RechargeRepositoryImpl): RechargeRepository
}