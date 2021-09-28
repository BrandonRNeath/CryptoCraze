package com.nemesisprotocol.cryptocraze.presentation.info_screen.coin_info_list

import com.nemesisprotocol.cryptocraze.domain.crypto_info.Coin


data class CoinInfoListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
