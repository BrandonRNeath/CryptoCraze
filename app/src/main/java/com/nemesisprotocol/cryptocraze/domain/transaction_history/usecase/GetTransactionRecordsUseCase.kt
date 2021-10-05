package com.nemesisprotocol.cryptocraze.domain.transaction_history.usecase

import com.nemesisprotocol.cryptocraze.domain.transaction_history.TransactionHistoryRepo
import com.nemesisprotocol.cryptocraze.domain.transaction_history.TransactionRecord
import javax.inject.Inject

class GetTransactionRecordsUseCase @Inject constructor(private val transactionHistoryRepo: TransactionHistoryRepo) {
    operator fun invoke(): List<TransactionRecord> = transactionHistoryRepo.getTransactionRecords()
}
