package com.nemesisprotocol.cryptocraze.domain.crypto_data

interface CryptoDataRepo {
    suspend fun getPageCryptos(page: Int, pageSize: Int): List<CryptoData>
    suspend fun getCryptoDataBySymbol(symbol: String): List<CryptoData>
}
