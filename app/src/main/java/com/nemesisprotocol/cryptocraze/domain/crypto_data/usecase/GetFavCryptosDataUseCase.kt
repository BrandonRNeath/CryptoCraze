package com.nemesisprotocol.cryptocraze.domain.crypto_data.usecase

import androidx.lifecycle.LiveData
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoData
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoFavDataRepo
import javax.inject.Inject

class GetFavCryptosDataUseCase @Inject constructor(private val cryptoFavDataRepo: CryptoFavDataRepo) {
    suspend operator fun invoke(): LiveData<List<CryptoData>> = cryptoFavDataRepo.getFavCryptos()
}
