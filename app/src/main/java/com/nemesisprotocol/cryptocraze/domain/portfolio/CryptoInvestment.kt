package com.nemesisprotocol.cryptocraze.domain.portfolio

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "crypto_investments")
data class CryptoInvestment(
    @PrimaryKey val cryptoInvestmentId: String = UUID.randomUUID().toString(),
    @ColumnInfo val cryptoSymbol: String,
    @ColumnInfo val cryptoAmount: Double
)
