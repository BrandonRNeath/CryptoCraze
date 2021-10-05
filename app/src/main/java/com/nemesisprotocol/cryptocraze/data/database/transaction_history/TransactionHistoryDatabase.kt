package com.nemesisprotocol.cryptocraze.data.database.transaction_history

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nemesisprotocol.cryptocraze.data.database.TimestampConverters
import com.nemesisprotocol.cryptocraze.domain.transaction_history.TransactionRecord

@Database(entities = [TransactionRecord::class], version = 1, exportSchema = false)
@TypeConverters(TimestampConverters::class)
abstract class TransactionHistoryDatabase : RoomDatabase() {
    abstract fun transactionHistoryDao(): TransactionHistoryDao
}
