package com.nemesisprotocol.cryptocraze.data.database.converters

import androidx.room.TypeConverter
import com.nemesisprotocol.cryptocraze.presentation.crypto_transaction_screen.TransactionType

class TransactionTypeConverters {

    @TypeConverter
    fun toTransactionType(value: String) = enumValueOf<TransactionType>(value)

    @TypeConverter
    fun fromTransactionType(transactionType: TransactionType) =
        transactionType.name
}
