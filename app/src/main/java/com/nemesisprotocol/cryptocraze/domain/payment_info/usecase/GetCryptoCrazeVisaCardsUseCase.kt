package com.nemesisprotocol.cryptocraze.domain.payment_info.usecase

import com.nemesisprotocol.cryptocraze.domain.payment_info.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.domain.payment_info.PaymentInfoRepo
import javax.inject.Inject

class GetCryptoCrazeVisaCardsUseCase @Inject constructor(private val paymentInfoRepo: PaymentInfoRepo) {
    operator fun invoke(): List<CryptoCrazeVisaCard> = paymentInfoRepo.getCryptoCrazeVisaCards()
}
