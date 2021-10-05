package com.nemesisprotocol.cryptocraze.data.database.crypto_fav_data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nemesisprotocol.cryptocraze.data.database.ListTypeConverters
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoData

@Database(entities = [CryptoData::class], version = 1, exportSchema = false)
@TypeConverters(ListTypeConverters::class)
abstract class CryptoFavDataDatabase : RoomDatabase() {
    abstract fun cryptoFavDataDao(): CryptoFavDataDao
}
