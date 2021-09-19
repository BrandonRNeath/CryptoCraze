package com.nemesisprotocol.cryptocraze.data.di

import com.nemesisprotocol.cryptocraze.common.Constants
import com.nemesisprotocol.cryptocraze.data.crypto_data.remote.CryptoDataApi
import com.nemesisprotocol.cryptocraze.data.crypto_data.remote.CryptoDataApiMapper
import com.nemesisprotocol.cryptocraze.data.repos.CryptoDataRepoImpl
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoDataRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCryptoDataApi(): CryptoDataApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoDataApi::class.java)
    }

    @Provides
    fun provideCryptoDataMapper() : CryptoDataApiMapper = CryptoDataApiMapper()

    @Provides
    @Singleton
    fun provideCryptoDataRepo(cryptoDataRepoImpl: CryptoDataRepoImpl) : CryptoDataRepo = cryptoDataRepoImpl

}