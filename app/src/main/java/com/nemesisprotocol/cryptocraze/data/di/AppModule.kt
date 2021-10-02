package com.nemesisprotocol.cryptocraze.data.di

import com.nemesisprotocol.cryptocraze.common.Constants
import com.nemesisprotocol.cryptocraze.common.DispatcherProvider
import com.nemesisprotocol.cryptocraze.data.crypto_data.remote.CryptoDataApi
import com.nemesisprotocol.cryptocraze.data.crypto_data.remote.CryptoDataApiMapper
import com.nemesisprotocol.cryptocraze.data.crypto_info.remote.CoinPaprikaApi
import com.nemesisprotocol.cryptocraze.data.repos.CoinRepositoryImpl
import com.nemesisprotocol.cryptocraze.data.repos.CryptoDataRepoImpl
import com.nemesisprotocol.cryptocraze.data.repos.PaymentInfoRepoImpl
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoDataRepo
import com.nemesisprotocol.cryptocraze.domain.crypto_info.CoinRepo
import com.nemesisprotocol.cryptocraze.domain.payment_info.PaymentInfoRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
            .baseUrl(Constants.COIN_GECKO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoDataApi::class.java)
    }

    @Provides
    fun provideCryptoDataMapper(): CryptoDataApiMapper = CryptoDataApiMapper()

    @Provides
    @Singleton
    fun provideCryptoDataRepo(cryptoDataRepoImpl: CryptoDataRepoImpl): CryptoDataRepo =
        cryptoDataRepoImpl

    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.COIN_PAPRIKA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepo(coinRepositoryImpl: CoinRepositoryImpl): CoinRepo = coinRepositoryImpl

    @Provides
    @Singleton
    fun providePaymentInfoRepo(paymentInfoRepoImpl: PaymentInfoRepoImpl): PaymentInfoRepo =
        paymentInfoRepoImpl

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

}
