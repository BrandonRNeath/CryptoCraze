package com.nemesisprotocol.cryptocraze.data.repos

import androidx.lifecycle.LiveData
import com.nemesisprotocol.cryptocraze.data.database.crypto_fav_data.CryptoFavDataDao
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoData
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoFavDataRepo
import javax.inject.Inject

class CryptoFavDataRepoImpl @Inject constructor(private val cryptoFavDataDao: CryptoFavDataDao) :
    CryptoFavDataRepo {

    override suspend fun getFavCryptos(): LiveData<List<CryptoData>> {
        return cryptoFavDataDao.getFavCryptos()
    }

    override suspend fun addFav(favCryptoData: CryptoData) {
        cryptoFavDataDao.addFav(favCryptoData)
    }

    override suspend fun removeFav(cryptoData: CryptoData) {
        cryptoFavDataDao.removeFav(cryptoData)
    }

    override suspend fun wipeFavorites() {
        cryptoFavDataDao.wipeFavorites()
    }

}
