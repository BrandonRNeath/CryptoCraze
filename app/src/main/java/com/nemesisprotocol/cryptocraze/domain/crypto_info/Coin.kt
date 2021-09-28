package com.nemesisprotocol.cryptocraze.domain.crypto_info

data class Coin(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)
