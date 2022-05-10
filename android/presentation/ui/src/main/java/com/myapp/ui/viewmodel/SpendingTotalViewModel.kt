package com.myapp.ui.viewmodel

import com.myapp.component.component.ChartValue
import com.myapp.composesample.util.base.BaseContract
import com.myapp.composesample.util.base.BaseViewModel
import com.myapp.kmmsample.usecase.CoinUseCase
import com.myapp.model.value.RealAssets
import com.myapp.model.value.StepnCoinType
import com.myapp.presentation.extension.chartColor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Spending管理画面 UIロジック
 *
 */
@HiltViewModel
class SpendingTotalViewModel @Inject constructor(
    coinUseCase: CoinUseCase
) : BaseViewModel<SpendingContract.State, SpendingContract.Effect, SpendingContract.Event>() {

    init {
        val spending = coinUseCase.getSpendingCoin()
        val rates = coinUseCase.getRateCoin()
        val chartValues = spending.values()
            .map{
                ChartValue(
                    it.type().label,
                    it.value.toString(),
                    if (it is RealAssets) {
                        it.value * rates.getRate(it.type()).value * rates.getRate(StepnCoinType.SOL).value
                    } else {
                        it.value * rates.getRate(it.type()).value
                    },
                    it.type().chartColor()
                )
            }
        setState { copy(chartValue = chartValues) }
    }

    override fun initState(): SpendingContract.State = SpendingContract.State()

    override fun handleEvents(event: SpendingContract.Event) {}

}

/**
 * Spending管理画面 UI状態管理
 *
 */
interface SpendingContract : BaseContract {

    /**
     * 状態保持
     *
     * @property chartValue チャートデータ
     */
    data class State(
        val chartValue: List<ChartValue> = listOf()
    ) : BaseContract.State

    /**
     * UIイベント
     *
     */
    sealed class Effect : BaseContract.Effect

    /**
     * アクション
     *
     */
    sealed class Event : BaseContract.Event
}