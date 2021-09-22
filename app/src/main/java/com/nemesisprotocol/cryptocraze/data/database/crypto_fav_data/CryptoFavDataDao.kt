package com.nemesisprotocol.cryptocraze.data.database.crypto_fav_data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.TypeConverters
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoData

@Dao
@TypeConverters()
interface CryptoFavDataDao {

    @Transaction
    @Query("SELECT * FROM crypto_favorites")
    fun getFavCryptos(): LiveData<List<CryptoData>>

    @Insert
    fun addFav(favCrypto: CryptoData)

    @Delete
    fun removeFav(crypto: CryptoData)

    @Query("SELECT EXISTS(SELECT * FROM crypto_favorites WHERE name = :cryptoName)")
    fun checkFavCryptoExists(cryptoName: String): Boolean

    @Query("DELETE FROM crypto_favorites")
    fun wipeFavorites()
}
