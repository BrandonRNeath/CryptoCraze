package com.nemesisprotocol.cryptocraze.domain.portfolio

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_investments")
data class CryptoInvestment(
    @PrimaryKey val cryptoSymbol: String,
    @ColumnInfo val cryptoAmount: Double
)
