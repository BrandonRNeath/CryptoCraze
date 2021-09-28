package com.nemesisprotocol.cryptocraze.domain.crypto_info

import com.nemesisprotocol.cryptocraze.data.crypto_info.remote.dto.CoinDto
import com.nemesisprotocol.cryptocraze.data.crypto_info.remote.dto.CoinInfoDto

interface CoinRepo {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinInfoDto
}