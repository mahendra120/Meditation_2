//package com.example.meditation
//
//import androidx.compose.foundation.layout.height
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import co.yml.charts.axis.AxisData
//import co.yml.charts.axis.DataCategoryOptions
//import co.yml.charts.common.model.Point
//import co.yml.charts.common.utils.DataUtils
//import co.yml.charts.ui.barchart.BarChart
//import co.yml.charts.ui.barchart.models.BarChartData
//import co.yml.charts.ui.barchart.models.BarChartType
//
//
//@Composable
//fun BarChart12() {
//    val pointsData: List<Point> =
//        listOf(Point(0f, 40f), Point(1f, 90f), Point(2f, 0f), Point(3f, 60f), Point(4f, 10f))
//
//    val maxRange = 100
//    val yStepSize = 5
//
//    val barChartData = DataUtils.getBarChartData(
//        listSize = pointsData.size,
//        maxRange = 150,
//        barChartType = BarChartType.HORIZONTAL,
//        dataCategoryOptions = DataCategoryOptions(),
//    )
//
//
//    val xAxisData = AxisData.Builder()
//        .axisStepSize(30.dp)
//        .steps(barChartData.size - 1)
//        .bottomPadding(40.dp)
//        .axisLabelAngle(20f)
//        .labelData { index -> "" }
//        .build()
//
//    val yAxisData = AxisData.Builder()
//        .steps(yStepSize)
//        .labelAndAxisLinePadding(20.dp)
//        .axisOffset(20.dp)
//        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
//        .build()
//
//    val barChartDataModel = BarChartData(
//        chartData = barChartData,
//        xAxisData = xAxisData,
//        yAxisData = yAxisData,
////        paddingBetweenBars = 20.dp,
////        barWidth = 25.dp
//    )
//
//    BarChart(modifier = Modifier.height(350.dp), barChartData = barChartDataModel)
//
//}