package com.nemesisprotocol.cryptocraze.data.database

import androidx.room.TypeConverter
import com.nemesisprotocol.cryptocraze.presentation.wallet_screen.CryptoCrazeVisaColour

class CryptoCrazeVisaColourConverters {

    @TypeConverter
    fun toCryptoCrazeVisaColour(value: String) = enumValueOf<CryptoCrazeVisaColour>(value)

    @TypeConverter
    fun fromCryptoCrazeVisaColour(cryptoCrazeVisaColour: CryptoCrazeVisaColour) =
        cryptoCrazeVisaColour.name
}
