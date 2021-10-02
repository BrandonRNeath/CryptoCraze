package com.nemesisprotocol.cryptocraze.domain.payment_info

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nemesisprotocol.cryptocraze.presentation.wallet_screen.CryptoCrazeVisaColour

@Entity(tableName = "crypto_craze_visa_card_info")
data class CryptoCrazeVisaCard(
    @PrimaryKey(autoGenerate = true) val cardId: Int = 0,
    @ColumnInfo val cryptoCrazeVisaColour: CryptoCrazeVisaColour
)
