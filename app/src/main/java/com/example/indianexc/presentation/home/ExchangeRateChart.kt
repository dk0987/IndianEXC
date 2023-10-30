package com.example.indianexc.presentation.home

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.indianexc.domain.modal.ExchangeRate
import com.example.indianexc.util.round
import com.example.indianexc.util.toDay

@Composable
fun ExchangeRateChart(
    data: List<ExchangeRate>,
    modifier : Modifier = Modifier,
    selectedIndex : Int = 0
) {
    val spacing = 120f

    val maxvalue = remember(data) {
        data.maxOfOrNull { it.latestCurrencies[selectedIndex].rate }?.round() ?: 0.00001
    }

    val minvalue = remember(data) {
        data.minOfOrNull { it.latestCurrencies[selectedIndex].rate }?.round() ?: 0.00000
    }

    val transparentGraphColor = remember {
        Color(0xFF391285).copy(alpha = 0.2f)
    }

    val density = LocalDensity.current

    val textPaint = remember(density) {
        android.graphics.Paint().apply {
            color = android.graphics.Color.BLACK
            textAlign = android.graphics.Paint.Align.CENTER
            textSize = density.run { 10.sp.toPx() }
        }
    }

    Canvas(modifier = modifier) {

        val spacePerDay = (size.width - spacing) / data.size

        (0 until  data.size - 1).forEach { i ->
            val info = data[i]
            val day = info.timestamp.toDay()
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    day.toString(),
                    spacing + i * spacePerDay,
                    size.height - 5,
                    textPaint
                )
            }
        }

        val priceStep = (maxvalue - minvalue) / 5f
        (0..5).forEach { i ->
            drawContext.canvas.nativeCanvas.apply {
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        (minvalue + priceStep * i).round().toString(),
                        30f,
                        size.height - spacing - i * size.height / 5f,
                        textPaint
                    )
                }
            }
            var lastX = 0f
            val strokePath = Path().apply {
                val height = size.height

                for (i in data.indices) {
                    val exchangeRate = data[i]
                    val nextInfo = data.getOrNull(i + 1) ?: data.last()
                    val leftRatio =
                        (exchangeRate.latestCurrencies[selectedIndex].rate.round() - minvalue) / (maxvalue - minvalue)
                    val rightRatio =
                        (nextInfo.latestCurrencies[selectedIndex].rate.round() - minvalue) / (maxvalue - minvalue)

                    val x1 = spacing + i * spacePerDay
                    val y1 = height - spacing - (leftRatio * height).toFloat()
                    val x2 = spacing + (i + 1) * spacePerDay
                    val y2 = height - spacing - (rightRatio * height).toFloat()

                    if (i == 0) {
                        moveTo(x1, y1)
                    }
                    lastX = (x1 + x2) / 2f
                    quadraticBezierTo(
                        x1, y1, lastX, (y1 + y2) / 2f
                    )
                }
            }
            val fillPath = android.graphics.Path(strokePath.asAndroidPath())
                .asComposePath()
                .apply {
                    lineTo(lastX, size.height - spacing)
                    lineTo(spacing, size.height - spacing)
                    close()
                }
            drawPath(
                path = fillPath,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        transparentGraphColor,
                        Color.Transparent
                    ),
                    endY = size.height - spacing
                )
            )
            drawPath(
                path = strokePath,
                color = Color(0xFF391285),
                style = Stroke(
                    width = 0.5.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
    }
}