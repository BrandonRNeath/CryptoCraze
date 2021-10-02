package com.nemesisprotocol.cryptocraze.data.database.payment_info

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nemesisprotocol.cryptocraze.data.database.CryptoCrazeVisaColourConverters
import com.nemesisprotocol.cryptocraze.domain.payment_info.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.domain.payment_info.FiatWalletCard

@Database(
    entities = [FiatWalletCard::class, CryptoCrazeVisaCard::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CryptoCrazeVisaColourConverters::class)
abstract class PaymentInfoDatabase : RoomDatabase() {
    abstract fun paymentInfoDao(): PaymentInfoDao
}
