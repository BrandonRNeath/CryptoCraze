package com.nemesisprotocol.cryptocraze.presentation.portfolio_screen

import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nemesisprotocol.cryptocraze.presentation.portfolio_screen.piechart.PieChart
import com.nemesisprotocol.cryptocraze.presentation.portfolio_screen.piechart.PieChartData
import com.nemesisprotocol.cryptocraze.presentation.portfolio_screen.piechart.PieChartData.Slice
import com.nemesisprotocol.cryptocraze.presentation.portfolio_screen.piechart.renderer.SimpleSliceDrawer

@Composable
fun PortfolioScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    ) {
        Text(
            text = "Portfolio Value",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontStyle = FontStyle.Italic,
            fontSize = 18.sp
        )
        Text(
            text = "£32,740.84 GBP",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.Center, Alignment.CenterHorizontally,
        ) {
            PieChart(
                pieChartData = PieChartData(
                    listOf(
                        Slice(1f, Color.Green),
                        Slice(6f, Color.Red),
                        Slice(6f, Color.Blue)
                    )
                ),
                modifier = Modifier.size(128.dp),
                animation = TweenSpec(durationMillis = 500),
                sliceDrawer = SimpleSliceDrawer(30f)
            )
        }

        Text(
            text = "ETH",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Green
        )
        Text(
            text = "45%",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            textAlign = TextAlign.Start,
            fontStyle = FontStyle.Italic,
            fontSize = 14.sp
        )
        Text(
            text = "BTC",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Blue
        )
        Text(
            text = "45%",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            textAlign = TextAlign.Start,
            fontStyle = FontStyle.Italic,
            fontSize = 14.sp
        )
        Text(
            text = "ADA",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Red
        )
        Text(
            text = "10%",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            textAlign = TextAlign.Start,
            fontStyle = FontStyle.Italic,
            fontSize = 14.sp
        )
    }
}
