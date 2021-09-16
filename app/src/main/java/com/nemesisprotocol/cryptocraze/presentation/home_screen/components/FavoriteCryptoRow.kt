package com.nemesisprotocol.cryptocraze.presentation.home_screen.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nemesisprotocol.cryptocraze.presentation.LineChart
import com.nemesisprotocol.cryptocraze.presentation.ui.theme.gradientGreenColors
import kotlin.random.Random

@Composable
fun FavouriteCryptoRow(cryptoName: String) {
    Card(modifier = Modifier
        .height(64.dp)
        .fillMaxWidth()
        .padding(top = 16.dp, start = 8.dp, end = 8.dp),
        backgroundColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    ) {
        Row {
            Text(
                color =  if (isSystemInDarkTheme()) Color.Black else Color.White,
                text = cryptoName
            )
            LineChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                yAxisValues = createRandomFloatList(),
                lineColors =  gradientGreenColors
            )
        }
    }
}

fun createRandomFloatList(): List<Float> {
    val list = mutableListOf<Float>()
    (0..20).forEach { _ ->
        list.add(Random.nextFloat() * 10)
    }
    return list
}

