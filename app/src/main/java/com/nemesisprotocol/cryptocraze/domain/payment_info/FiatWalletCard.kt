package com.nemesisprotocol.cryptocraze.domain.payment_info

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fiat_wallet_card_info")
data class FiatWalletCard(
    @PrimaryKey val cardNumber : Long,
    @ColumnInfo val cardName : String,
    @ColumnInfo val expiryNumber : Int,
    @ColumnInfo val cvvNumber : Int
)
