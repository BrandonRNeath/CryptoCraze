package com.nemesisprotocol.cryptocraze.domain.crypto_data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "crypto_favorites")
data class CryptoData(
    @PrimaryKey
    val symbol: String,
    val price: Double,
    val name: String,
    val image: String,
    val dailyChange: Double,
    val dailyChangePercentage: Double,
    val high: Double,
    val low: Double,
    val marketCap: Long,
    val volume: Double,
    val supply: Double?,
    val chartData: List<Float>
) : Serializable {

    override fun equals(other: Any?): Boolean {
        return (other as CryptoData).symbol == symbol
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + dailyChange.hashCode()
        result = 31 * result + dailyChangePercentage.hashCode()
        result = 31 * result + high.hashCode()
        result = 31 * result + low.hashCode()
        result = 31 * result + marketCap.hashCode()
        result = 31 * result + volume.hashCode()
        result = 31 * result + (supply?.hashCode() ?: 0)
        result = 31 * result + chartData.hashCode()
        return result
    }

}