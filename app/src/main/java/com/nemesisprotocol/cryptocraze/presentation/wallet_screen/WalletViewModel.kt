package com.nemesisprotocol.cryptocraze.presentation.wallet_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val getFiatWalletByCardNumberUseCase: GetFiatWalletByCardNumberUseCase
) : ViewModel() {

    suspend fun getCryptoCrazeVisaCards(): List<CryptoCrazeVisaCard> {
        return withContext(Dispatchers.IO) {
            getCryptoCrazeVisaCardsUseCase()
        }
    }

    suspend fun getCryptoCrazeVisaCardById(cardId: Int): CryptoCrazeVisaCard {
        return withContext(Dispatchers.IO) {
            getCryptoCrazeVisaCardByIdUseCase(cardId)
        }
    }

    suspend fun getFiatWallets(): List<FiatWalletCard> {
        return withContext(Dispatchers.IO) {
            getFiatWalletsUseCase()
        }
    }

    suspend fun getFiatWalletByCardNumber(cardNumber: Long): FiatWalletCard {
        return withContext(Dispatchers.IO) {
            getFiatWalletByCardNumberUseCase(cardNumber)
        }
    }

    fun addCryptoCrazeVisaCard(cryptoCrazeVisaCard: CryptoCrazeVisaCard) {
        viewModelScope.launch(Dispatchers.IO) {
            addCryptoCrazeVisaCardUseCase(cryptoCrazeVisaCard)
        }
    }

    fun addFiatWallet(fiatWalletCard: FiatWalletCard) {
        viewModelScope.launch(Dispatchers.IO) {
            addFiatWalletUseCase(fiatWalletCard)
        }
    }

    fun deleteCryptoCrazeVisaCard(cryptoCrazeVisaCard: CryptoCrazeVisaCard) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCryptoCrazeVisaCardUseCase(cryptoCrazeVisaCard)
        }
    }

    fun deleteFiatWallet(fiatWalletCard: FiatWalletCard) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFiatWalletUseCase(fiatWalletCard)
        }
    }
}
