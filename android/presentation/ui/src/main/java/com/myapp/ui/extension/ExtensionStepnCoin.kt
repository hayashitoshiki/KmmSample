package com.myapp.presentation.extension

import androidx.compose.ui.graphics.Color
import com.myapp.component.theme.*
import com.myapp.model.value.AssetsType
import com.myapp.model.value.RealAssetsType
import com.myapp.model.value.StepnCoinType


/**
 * StepnCoinTypeの色定義
 *
 * @return 各Labelに対するグラフ色
 */
fun AssetsType.chartColor() : Color {
    return when(this) {
        StepnCoinType.GMT -> gmtColor
        StepnCoinType.GST -> gstColor
        StepnCoinType.SOL -> solanaColor
        StepnCoinType.USCD -> usdcColor
        RealAssetsType.GEM -> gemColor
        RealAssetsType.SHOEBOX -> shoeboxColor
        RealAssetsType.SNEAKER -> sneakerColor
    }
}