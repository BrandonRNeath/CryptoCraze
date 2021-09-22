package com.nemesisprotocol.cryptocraze.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.nemesisprotocol.cryptocraze.domain.crypto_data.CryptoData
import com.nemesisprotocol.cryptocraze.extensions.roundToThreeDecimals
import com.nemesisprotocol.cryptocraze.extensions.roundToTwoDecimals
import com.nemesisprotocol.cryptocraze.presentation.LineChart
import com.nemesisprotocol.cryptocraze.presentation.home_screen.HomeViewModel
import com.nemesisprotocol.cryptocraze.presentation.ui.theme.gradientGreenColors
import com.nemesisprotocol.cryptocraze.presentation.ui.theme.gradientRedColors

@ExperimentalCoilApi
@Composable
fun CryptoDataListItem(
    cryptoData: CryptoData,
    isFav: MutableState<Boolean>,
    viewModel: HomeViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(data = cryptoData.image),
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.weight(0.4f)) {
            Text(
                text = cryptoData.symbol.uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = "$${cryptoData.price}",
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            LineChart(
                modifier = Modifier
                    .width(200.dp)
                    .height(70.dp)
                    .align(Alignment.CenterHorizontally),
                yAxisValues = cryptoData.chartData,
                lineColors = if (cryptoData.dailyChange > 0) gradientGreenColors else gradientRedColors
            )
            Text(
                text = cryptoData.dailyChange.roundToThreeDecimals() +
                    " (${cryptoData.dailyChangePercentage.roundToTwoDecimals()}%)",
                color = if (cryptoData.dailyChange > 0) Color.Green else Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.End)
            )
        }
        IconToggleButton(
            checked = isFav.value,
            onCheckedChange = {
                if (!isFav.value) {
                    viewModel.addFavCrypto(cryptoData)
                    isFav.value = !isFav.value
                } else {
                    viewModel.deleteFavCrypto(cryptoData)
                    isFav.value = !isFav.value
                }
            }
        ) {
            Icon(
                imageVector = if (isFav.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null,
                tint = if (isFav.value) Color.Red else MaterialTheme.colors.onSurface
            )
        }
    }
}
