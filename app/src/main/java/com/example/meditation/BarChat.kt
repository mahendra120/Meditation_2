package com.example.meditation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewBarChart() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
        BarChart()
    }
}

@Composable
fun BarChart() {
    val chartData = listOf(
        "Sun" to 5,
        "Mon" to 10,
        "Tue" to 12,
        "Wen" to 9,
        "Thu" to 20,
        "Fri" to 22,
        "Sat" to 15
    )

    val spacingFromLeft = 60f
    val spacingFromBottom = 40f
    val spacingFromTop = 30f

    val maxValue = 30f // Since you want Y-axis max to be 30
    val yLabels = listOf(7, 12, 16, 21, 30)

    val density = LocalDensity.current
    val textPaint = remember {
        android.graphics.Paint().apply {
            color = android.graphics.Color.WHITE
            textAlign = android.graphics.Paint.Align.CENTER
            textSize = with(density) { 12.sp.toPx() }
            isAntiAlias = true
        }
    }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(color = Color.Black)
            .padding(start = 0.dp,15.dp)
    ) {
        val canvasHeight = size.height
        val canvasWidth = size.width
        val spacePerData = (canvasWidth - spacingFromLeft) / chartData.size
        val barWidth = spacePerData * 0.6f

        // Draw Y-axis labels and grid lines
        val chartHeight = canvasHeight - spacingFromTop - spacingFromBottom

        yLabels.forEachIndexed { index, label ->
            val y = canvasHeight - spacingFromBottom - (label / maxValue) * chartHeight

            drawContext.canvas.nativeCanvas.drawText(
                label.toString(),
                30f,  // X-position of Y-axis labels
                y + 5f, // slight vertical correction
                textPaint
            )

            drawLine(
                color = Color.LightGray,
                start = Offset(spacingFromLeft, y),
                end = Offset(canvasWidth, y),
                strokeWidth = 1.dp.toPx()
            )
        }
        // 🔸 Y-axis main line
        drawLine(
            color = Color.White,
            start = Offset(spacingFromLeft, spacingFromTop),
            end = Offset(spacingFromLeft, canvasHeight - spacingFromBottom),
            strokeWidth = 2.dp.toPx()
        )

        // 🔸 X-axis main line
        drawLine(
            color = Color.White,
            start = Offset(spacingFromLeft, canvasHeight - spacingFromBottom),
            end = Offset(canvasWidth, canvasHeight - spacingFromBottom),
            strokeWidth = 2.dp.toPx()
        )

        // Draw bars and X-axis labels
        chartData.forEachIndexed { i, data ->
            val barHeight = (data.second / maxValue) * chartHeight
            val x = spacingFromLeft + i * spacePerData + (spacePerData - barWidth) / 2.5f
            val y = canvasHeight - spacingFromBottom - barHeight - 2.dp.toPx()

            drawRect(
                color = Color.Gray,
                topLeft = Offset(x, y),
                size = androidx.compose.ui.geometry.Size(barWidth, barHeight)
            )

            drawContext.canvas.nativeCanvas.drawText(
                data.first,
                x + barWidth / 2,
                canvasHeight - 7f,
                textPaint
            )
        }
    }
}
