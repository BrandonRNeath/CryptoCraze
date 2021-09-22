package com.nemesisprotocol.cryptocraze.domain.crypto_data.usecase

import android.util.Log
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoData
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoFavDataRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddFavCryptoDataUseCase @Inject constructor(private val cryptoFavDataRepo: CryptoFavDataRepo) {
    private val addFavCryptoDataCoroutineScope = CoroutineScope(Dispatchers.Default)
    operator fun invoke(cryptoData: CryptoData) =
        addFavCryptoDataCoroutineScope.launch {
            Log.d("Testing", cryptoData.chartData.toString())
            cryptoFavDataRepo.addFav(cryptoData)
        }
}
