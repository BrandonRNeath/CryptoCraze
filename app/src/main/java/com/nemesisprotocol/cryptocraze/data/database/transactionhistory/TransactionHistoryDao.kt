package com.nemesisprotocol.cryptocraze.data.database.transactionhistory

import androidx.room.*
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.TransactionRecord

@Dao
@TypeConverters()
interface TransactionHistoryDao {

    @Query("SELECT * FROM transaction_records")
    fun getTransactionRecords(): List<TransactionRecord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTransactionRecord(transactionRecord: TransactionRecord)

    @Delete
    fun deleteTransactionRecord(transactionRecord: TransactionRecord)
}
