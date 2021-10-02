package com.nemesisprotocol.cryptocraze.data.database.payment_info

import androidx.room.*
import com.nemesisprotocol.cryptocraze.domain.payment_info.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.domain.payment_info.FiatWalletCard

@Dao
@TypeConverters()
interface PaymentInfoDao {

    @Query("SELECT * FROM fiat_wallet_card_info")
    fun getFiatWallets(): List<FiatWalletCard>

    @Query("SELECT * FROM fiat_wallet_card_info WHERE cardNumber =:cardNumber")
    fun getFiatWalletByCardNumber(cardNumber: Long): FiatWalletCard

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFiatWallet(fiatWalletCard: FiatWalletCard)

    @Delete
    fun deleteFiatWallet(fiatWalletCard: FiatWalletCard)

    @Query("SELECT * from crypto_craze_visa_card_info")
    fun getCryptoCrazeVisaCards(): List<CryptoCrazeVisaCard>

    @Query("SELECT * FROM crypto_craze_visa_card_info WHERE cardId =:cardId")
    fun getCryptoCrazeVisaCardByCardId(cardId: Int): CryptoCrazeVisaCard

    @Insert
    fun addCryptoCrazeVisaCard(cryptoCrazeVisaCard: CryptoCrazeVisaCard)

    @Delete
    fun deleteCryptoCrazeVisaCard(cryptoCrazeVisaCard: CryptoCrazeVisaCard)

}
