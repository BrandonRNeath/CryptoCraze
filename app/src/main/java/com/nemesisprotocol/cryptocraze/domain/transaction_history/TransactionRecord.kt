package com.nemesisprotocol.cryptocraze.domain.transaction_history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nemesisprotocol.cryptocraze.presentation.crypto_transaction_screen.TransactionType
import java.util.*

@Entity(tableName = "transaction_records")
data class TransactionRecord(
    @PrimaryKey val transactionUuid: String = UUID.randomUUID().toString(),
    @ColumnInfo val cryptoSymbol: String,
    @ColumnInfo val cryptoAmount: Double,
    @ColumnInfo val amount: String,
    @ColumnInfo val timestamp: Date = Date(),
    @ColumnInfo val transactionType: TransactionType
)
