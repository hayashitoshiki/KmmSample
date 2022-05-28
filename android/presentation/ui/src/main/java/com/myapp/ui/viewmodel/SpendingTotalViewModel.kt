package com.myapp.ui.viewmodel

import com.myapp.component.component.ChartValue
import com.myapp.composesample.util.base.BaseContract
import com.myapp.composesample.util.base.BaseViewModel
import com.myapp.kmmsample.usecase.CoinUseCase
import com.myapp.model.value.LegalTender
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
        val chartValues = coinUseCase.getSpendingCoin()
            .values()
            .map{
                ChartValue(
                    it.type().label,
                    it.value.toString(),
                    coinUseCase.changeStableRate(it, LegalTender.JPY),
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