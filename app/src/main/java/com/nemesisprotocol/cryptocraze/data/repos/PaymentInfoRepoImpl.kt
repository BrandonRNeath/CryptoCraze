package com.nemesisprotocol.cryptocraze.data.repos

import com.nemesisprotocol.cryptocraze.data.database.payment_info.PaymentInfoDao
import com.nemesisprotocol.cryptocraze.domain.payment_info.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.domain.payment_info.FiatWalletCard
import com.nemesisprotocol.cryptocraze.domain.payment_info.PaymentInfoRepo
import javax.inject.Inject

class PaymentInfoRepoImpl @Inject constructor(private val paymentInfoDao: PaymentInfoDao) :
    PaymentInfoRepo {

    override fun getFiatWallets(): List<FiatWalletCard> {
        return paymentInfoDao.getFiatWallets()
    }

    override fun getFiatWalletByCardNumber(cardNumber: Long): FiatWalletCard {
        return paymentInfoDao.getFiatWalletByCardNumber(cardNumber = cardNumber)
    }

    override fun addFiatWallet(fiatWalletCard: FiatWalletCard) {
        paymentInfoDao.addFiatWallet(fiatWalletCard = fiatWalletCard)
    }

    override fun deleteFiatWallet(fiatWalletCard: FiatWalletCard) {
        paymentInfoDao.deleteFiatWallet(fiatWalletCard = fiatWalletCard)
    }

    override fun getCryptoCrazeVisaCards(): List<CryptoCrazeVisaCard> {
        return paymentInfoDao.getCryptoCrazeVisaCards()
    }

    override fun getCryptoCrazeVisaCardByCardId(cardId: Int): CryptoCrazeVisaCard {
        return paymentInfoDao.getCryptoCrazeVisaCardByCardId(cardId = cardId)
    }

    override fun addCryptoCrazeVisaCard(cryptoCrazeVisaCard: CryptoCrazeVisaCard) {
        paymentInfoDao.addCryptoCrazeVisaCard(cryptoCrazeVisaCard = cryptoCrazeVisaCard)
    }

    override fun deleteCryptoCrazeVisaCard(cryptoCrazeVisaCard: CryptoCrazeVisaCard) {
        paymentInfoDao.deleteCryptoCrazeVisaCard(cryptoCrazeVisaCard)
    }
}
