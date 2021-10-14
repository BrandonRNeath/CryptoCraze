package com.nemesisprotocol.cryptocraze.data.database.portfolio

import androidx.room.*
import com.nemesisprotocol.cryptocraze.domain.portfolio.CryptoInvestment

@Dao
interface PortfolioDao {

    @Query("SELECT * FROM crypto_investments")
    fun getPortfolio(): List<CryptoInvestment>

    @Insert
    fun addCryptoInvestment(cryptoInvestment: CryptoInvestment)

    @Update
    fun updateCryptoInvestment(cryptoInvestment: CryptoInvestment)

    @Delete
    fun deleteCryptoInvestment(cryptoInvestment: CryptoInvestment)

    @Query("DELETE FROM crypto_investments")
    fun wipePortfolio()
}