package com.nemesisprotocol.cryptocraze.data.crypto_info.remote

import com.nemesisprotocol.cryptocraze.data.crypto_info.remote.dto.CoinDto
import com.nemesisprotocol.cryptocraze.data.crypto_info.remote.dto.CoinInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinInfoDto
}
