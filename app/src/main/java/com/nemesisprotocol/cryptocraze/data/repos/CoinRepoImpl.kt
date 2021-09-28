package com.nemesisprotocol.cryptocraze.data.repos

import com.nemesisprotocol.cryptocraze.data.crypto_info.remote.CoinPaprikaApi
import com.nemesisprotocol.cryptocraze.data.crypto_info.remote.dto.CoinDto
import com.nemesisprotocol.cryptocraze.data.crypto_info.remote.dto.CoinInfoDto
import com.nemesisprotocol.cryptocraze.domain.crypto_info.CoinRepo
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepo {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinInfoDto {
        return api.getCoinById(coinId)
    }
}
