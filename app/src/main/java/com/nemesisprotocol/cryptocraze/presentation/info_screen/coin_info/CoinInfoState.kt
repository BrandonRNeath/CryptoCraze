package com.nemesisprotocol.cryptocraze.presentation.info_screen.coin_info

import com.nemesisprotocol.cryptocraze.domain.crypto_info.CoinDetail

data class CoinInfoState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
