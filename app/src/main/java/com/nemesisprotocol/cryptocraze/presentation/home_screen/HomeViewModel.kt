package com.nemesisprotocol.cryptocraze.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.nemesisprotocol.cryptocraze.data.paging.PageNumberSource
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoData
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoDataRepo
import com.nemesisprotocol.cryptocraze.domain.crypto_data.usecase.AddFavCryptoDataUseCase
import com.nemesisprotocol.cryptocraze.domain.crypto_data.usecase.GetFavCryptosDataUseCase
import com.nemesisprotocol.cryptocraze.domain.crypto_data.usecase.RemoveFavCryptoDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFavCryptoDataUseCase: GetFavCryptosDataUseCase,
    private val addFavCryptoDataUseCase: AddFavCryptoDataUseCase,
    private val removeFavCryptoDataUseCase: RemoveFavCryptoDataUseCase,
    private val cryptoDataRepo: CryptoDataRepo,
) : ViewModel() {

    val favCryptoLiveData = liveData(Dispatchers.IO) {
        emitSource(getFavCryptoDataUseCase())
    }

    fun getAllCryptos(pageSize: Int = 20) =
        Pager(config = PagingConfig(pageSize = pageSize, initialLoadSize = pageSize)) {
            PageNumberSource { pageNo, pageSize ->
                cryptoDataRepo.getPageCryptos(pageNo, pageSize)
            }
        }.flow.cachedIn(viewModelScope)

    fun addFavCrypto(
        symbol: String,
        price: Double,
        name: String,
        image: String,
        dailyChange: Double,
        dailyChangePercentage: Double,
        high: Double,
        low: Double,
        marketCap: Long,
        volume: Double,
        supply: Double?,
        chartData: List<Float>
    ) {
        addFavCryptoDataUseCase(
            CryptoData(
                symbol,
                price,
                name,
                image,
                dailyChange,
                dailyChangePercentage,
                high,
                low,
                marketCap,
                volume,
                supply,
                chartData
            )
        )
    }

    fun deleteFavCrypto(
        symbol: String,
        price: Double,
        name: String,
        image: String,
        dailyChange: Double,
        dailyChangePercentage: Double,
        high: Double,
        low: Double,
        marketCap: Long,
        volume: Double,
        supply: Double?,
        chartData: List<Float>
    ) {
        removeFavCryptoDataUseCase(
            CryptoData(
                symbol,
                price,
                name,
                image,
                dailyChange,
                dailyChangePercentage,
                high,
                low,
                marketCap,
                volume,
                supply,
                chartData
            )
        )
    }
}
