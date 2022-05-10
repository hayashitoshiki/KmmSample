package com.myapp.component.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.text.DecimalFormat


/**
 * チャート用のValue Object
 *
 * @property label ラベル
 * @property value データ値
 * @property color データの色
 */
data class ChartValue(
    val label: String,
    val subtitle: String,
    val value: Float,
    val color: Color
)


/**
 * 総合計用チャートコンポーネント
 *
 * @param modifier レイアウト
 * @param items データ
 * @param titleLabel データタイトル
 */
@Composable
fun TotalChartContent(
    modifier: Modifier = Modifier,
    items: List<ChartValue>,
    titleLabel: String
) {
    val row: @Composable (ChartValue) -> Unit = { item -> SubTitleChartRow(chartValue = item) }
    DonutsChartContent(
        modifier = modifier,
        items = items,
        titleLabel = titleLabel,
        row = row
    )
}

/**
 * 詳細値用コンポーネント
 *
 * @param modifier レイアウト
 * @param items データ
 * @param titleLabel データタイトル
 */
@Composable
fun DetailsChartContent(
    modifier: Modifier = Modifier,
    items: List<ChartValue>,
    titleLabel: String
) {
    val row: @Composable (ChartValue) -> Unit = { item ->  TextChartRow(item) }
    DonutsChartContent(
        modifier = modifier,
        items = items,
        titleLabel = titleLabel,
        row = row
    )
}


/**
 * ドーナッツ型円グラフ + 中央ラベルありコンポーネント
 *
 * @param modifier レイアウト
 * @param items データリスト
 * @param titleLabel グラフのタイトルラベル
 * @param row 凡例
 */
@Composable
fun DonutsChartContent(
    modifier: Modifier = Modifier,
    items: List<ChartValue>,
    titleLabel: String,
    row: @Composable (ChartValue) -> Unit
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Box(Modifier.padding(16.dp)) {
            val proportions = items.extractProportions { it.value }
            val circleColors = items.map { it.color }
            val amountsTotal = items.map { it.value }.sum()

            // グラフ出力
            AnimatedCircleGraph(
                proportions,
                circleColors,
                Modifier
                    .height(300.dp)
                    .align(Alignment.Center)
                    .fillMaxWidth()
            )

            // 中央テキスト
            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    text = titleLabel,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = formatAmount(amountsTotal) + "円",
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
        Spacer(Modifier.height(10.dp))

        // ラベル出力
        Card {
            Column(modifier = Modifier.padding(12.dp)) {
                items.forEach { item -> row(item) }
            }
        }
    }
}


/**
 * 合計値フォーマット
 *
 * @param amount 合計値
 * @return [#,###.##]区切りの合計値
 */
fun formatAmount(amount: Float): String {
    return AmountDecimalFormat.format(amount)
}

private val AmountDecimalFormat = DecimalFormat("#,###.##")

/**
 * 割合出力
 */
fun <E> List<E>.extractProportions(selector: (E) -> Float): List<Float> {
    val total = this.sumOf { selector(it).toDouble() }
    return this.map { (selector(it) / total).toFloat() }
}