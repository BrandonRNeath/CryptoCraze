package com.nemesisprotocol.cryptocraze.data.di

import android.content.Context
import androidx.room.Room
import com.nemesisprotocol.cryptocraze.data.database.UserDao
import com.nemesisprotocol.cryptocraze.data.database.UserDatabase
import com.nemesisprotocol.cryptocraze.data.database.UserRepoImpl
import com.nemesisprotocol.cryptocraze.domain.UserRepo
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
    fun provideUserRepo(userRepoImpl: UserRepoImpl) : UserRepo = userRepoImpl

}
