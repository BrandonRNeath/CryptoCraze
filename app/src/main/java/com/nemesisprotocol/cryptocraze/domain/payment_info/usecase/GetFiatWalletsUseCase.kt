package com.nemesisprotocol.cryptocraze.domain.payment_info.usecase

import com.nemesisprotocol.cryptocraze.domain.payment_info.FiatWalletCard
import com.nemesisprotocol.cryptocraze.domain.payment_info.PaymentInfoRepo
import javax.inject.Inject

class GetFiatWalletsUseCase @Inject constructor(private val paymentInfoRepo: PaymentInfoRepo) {
    operator fun invoke(): List<FiatWalletCard> = paymentInfoRepo.getFiatWallets()
}
