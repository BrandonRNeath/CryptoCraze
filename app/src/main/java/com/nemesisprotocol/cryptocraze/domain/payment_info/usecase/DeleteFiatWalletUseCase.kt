package com.nemesisprotocol.cryptocraze.domain.payment_info.usecase

import com.nemesisprotocol.cryptocraze.domain.payment_info.FiatWalletCard
import com.nemesisprotocol.cryptocraze.domain.payment_info.PaymentInfoRepo
import javax.inject.Inject

class DeleteFiatWalletUseCase @Inject constructor(private val paymentInfoRepo: PaymentInfoRepo) {
    operator fun invoke(fiatWalletCard: FiatWalletCard) =
        paymentInfoRepo.deleteFiatWallet(fiatWalletCard)
}