package com.nemesisprotocol.cryptocraze.domain.crypto_data.usecase

import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoData
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoFavDataRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RemoveFavCryptoDataUseCase @Inject constructor(private val cryptoFavDataRepo: CryptoFavDataRepo) {
    private val removeFavCryptoDataCoroutineScope = CoroutineScope(Dispatchers.Default)
    operator fun invoke(cryptoData: CryptoData) =
        removeFavCryptoDataCoroutineScope.launch { cryptoFavDataRepo.removeFav(cryptoData) }
}
