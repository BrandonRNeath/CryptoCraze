package com.nemesisprotocol.cryptocraze.domain.transaction_history

interface TransactionHistoryRepo {
    fun getTransactionRecords(): List<TransactionRecord>
    fun addTransactionRecord(transactionRecord: TransactionRecord)
    fun deleteTransactionRecord(transactionRecord: TransactionRecord)
}
