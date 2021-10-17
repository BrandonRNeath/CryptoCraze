package com.nemesisprotocol.cryptocraze.data.crypto_data.remote

import com.nemesisprotocol.cryptocraze.data.crypto_data.remote.dto.CryptoDataApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoDataApi {

    @GET("coins/markets?vs_currency=gbp&order=market_cap_desc&sparkline=true")
    suspend fun getAllCrypto(
        @Query("page") page: Int = 1,
        @Query("per_page") pageSize: Int = 20
    ): Response<List<CryptoDataApiResponse>>

    @GET("coins/markets?vs_currency=gbp&order=market_cap_desc&sparkline=false")
    suspend fun getCryptoDataBySymbol(
        @Query("symbols") symbol: String,
    ): Response<List<CryptoDataApiResponse>>
}
