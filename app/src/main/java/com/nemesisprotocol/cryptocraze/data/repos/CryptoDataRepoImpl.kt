package com.nemesisprotocol.cryptocraze.data.repos

import androidx.annotation.WorkerThread
import com.nemesisprotocol.cryptocraze.data.crypto_data.remote.CryptoDataApi
import com.nemesisprotocol.cryptocraze.data.crypto_data.remote.CryptoDataApiMapper
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoData
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoDataRepo
import javax.inject.Inject

class CryptoDataRepoImpl @Inject constructor(
    private val cryptoApi: CryptoDataApi,
    private val cryptoApiMapper: CryptoDataApiMapper
) : CryptoDataRepo {

    @WorkerThread
    override suspend fun getPageCryptos(page: Int, pageSize: Int): List<CryptoData> {
        val response = cryptoApi.getAllCrypto(page)
        return if (response.isSuccessful && !response.body().isNullOrEmpty()) {
            val cryptoApiResponseList = response.body()
            val cryptoList = cryptoApiResponseList?.map { cryptoApiResponse ->
                cryptoApiMapper.map(cryptoApiResponse)
            }
            cryptoList ?: emptyList()
        } else {
            emptyList()
        }
    }
}
