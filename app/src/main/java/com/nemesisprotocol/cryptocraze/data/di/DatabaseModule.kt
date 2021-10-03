package com.nemesisprotocol.cryptocraze.data.di

import android.content.Context
import androidx.room.Room
import com.nemesisprotocol.cryptocraze.data.database.crypto_fav_data.CryptoFavDataDao
import com.nemesisprotocol.cryptocraze.data.database.crypto_fav_data.CryptoFavDataDatabase
import com.nemesisprotocol.cryptocraze.data.database.payment_info.PaymentInfoDao
import com.nemesisprotocol.cryptocraze.data.database.payment_info.PaymentInfoDatabase
import com.nemesisprotocol.cryptocraze.data.database.user.UserDao
import com.nemesisprotocol.cryptocraze.data.database.user.UserDatabase
import com.nemesisprotocol.cryptocraze.data.repos.CryptoFavDataRepoImpl
import com.nemesisprotocol.cryptocraze.data.repos.UserRepoImpl
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoFavDataRepo
import com.nemesisprotocol.cryptocraze.domain.user.UserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, "user_db").build()
    }

    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao = userDatabase.userDao()

    @Provides
    @Singleton
    fun provideUserRepo(userRepoImpl: UserRepoImpl): UserRepo = userRepoImpl

    @Provides
    @Singleton
    fun provideCryptoDataDatabase(@ApplicationContext context: Context): CryptoFavDataDatabase {
        return Room.databaseBuilder(context, CryptoFavDataDatabase::class.java, "crypto_fav_data_db").build()
    }

    @Provides
    fun provideCryptoDataDao(cryptoFavDataDatabase: CryptoFavDataDatabase): CryptoFavDataDao = cryptoFavDataDatabase.cryptoFavDataDao()

    @Provides
    @Singleton
    fun provideCryptoFavDataRepo(cryptoFavDataRepoImpl: CryptoFavDataRepoImpl): CryptoFavDataRepo = cryptoFavDataRepoImpl

    @Provides
    @Singleton
    fun providePaymentInfoDatabase(@ApplicationContext context: Context): PaymentInfoDatabase {
        return Room.databaseBuilder(context, PaymentInfoDatabase::class.java, "payment_info_db").build()
    }

    @Provides
    fun providePaymentInfoDao(paymentInfoDatabase: PaymentInfoDatabase): PaymentInfoDao = paymentInfoDatabase.paymentInfoDao()
}
