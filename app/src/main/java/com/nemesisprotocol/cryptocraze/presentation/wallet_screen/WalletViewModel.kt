package com.nemesisprotocol.cryptocraze.presentation.wallet_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemesisprotocol.cryptocraze.common.DispatcherProvider
import com.nemesisprotocol.cryptocraze.domain.payment_info.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.domain.payment_info.FiatWalletCard
import com.nemesisprotocol.cryptocraze.domain.payment_info.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val addCryptoCrazeVisaCardUseCase: AddCryptoCrazeVisaCardUseCase,
    private val addFiatWalletUseCase: AddFiatWalletUseCase,
    private val deleteCryptoCrazeVisaCardUseCase: DeleteCryptoCrazeVisaCardUseCase,
    private val deleteFiatWalletUseCase: DeleteFiatWalletUseCase,
    private val getCryptoCrazeVisaCardsUseCase: GetCryptoCrazeVisaCardsUseCase,
    private val getCryptoCrazeVisaCardByIdUseCase: GetCryptoCrazeVisaCardByIdUseCase,
    private val getFiatWalletsUseCase: GetFiatWalletsUseCase,
    private val getFiatWalletByCardNumberUseCase: GetFiatWalletByCardNumberUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    suspend fun getCryptoCrazeVisaCards(): List<CryptoCrazeVisaCard> {
        return withContext(Dispatchers.IO) {
            getCryptoCrazeVisaCardsUseCase()
        }
    }

    suspend fun getCryptoCrazeVisaCardById(cardId: Int): CryptoCrazeVisaCard {
        return withContext(dispatcherProvider.io) {
            getCryptoCrazeVisaCardByIdUseCase(cardId)
        }
    }

    suspend fun getFiatWallets(): List<FiatWalletCard> {
        return withContext(dispatcherProvider.io) {
            getFiatWalletsUseCase()
        }
    }

    suspend fun getFiatWalletByCardNumber(cardNumber: Long): FiatWalletCard {
        return withContext(dispatcherProvider.io) {
            getFiatWalletByCardNumberUseCase(cardNumber)
        }
    }

    fun addCryptoCrazeVisaCard(cryptoCrazeVisaCard: CryptoCrazeVisaCard) {
        viewModelScope.launch(dispatcherProvider.io) {
            addCryptoCrazeVisaCardUseCase(cryptoCrazeVisaCard)
        }
    }

    fun addFiatWallet(fiatWalletCard: FiatWalletCard) {
        viewModelScope.launch(dispatcherProvider.io) {
            addFiatWalletUseCase(fiatWalletCard)
        }
    }

    fun deleteCryptoCrazeVisaCard(cryptoCrazeVisaCard: CryptoCrazeVisaCard) {
        viewModelScope.launch(dispatcherProvider.io) {
            deleteCryptoCrazeVisaCardUseCase(cryptoCrazeVisaCard)
        }
    }

    fun deleteFiatWallet(fiatWalletCard: FiatWalletCard) {
        viewModelScope.launch(dispatcherProvider.io) {
            deleteFiatWalletUseCase(fiatWalletCard)
        }
    }
}
