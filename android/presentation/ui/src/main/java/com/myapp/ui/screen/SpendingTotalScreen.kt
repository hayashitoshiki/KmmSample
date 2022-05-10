package com.myapp.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.myapp.component.component.DetailsChartContent
import com.myapp.ui.viewmodel.SpendingContract
import com.myapp.ui.viewmodel.SpendingTotalViewModel

/**
 * Spending管理画面
 *
 */
@Composable
fun SpendingTotalScreen(viewModel: SpendingTotalViewModel) {
    val state = viewModel.state.value
    SpendingTotalContent(state)
}

@Composable
private fun SpendingTotalContent(state: SpendingContract.State) {
    Column {
        DetailsChartContent(
            items = state.chartValue,
            titleLabel = "Spending　合計値"
        )
    }
}