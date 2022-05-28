package com.myapp.ui.viewmodel

import com.myapp.component.component.ChartValue
import com.myapp.composesample.util.base.BaseContract
import com.myapp.composesample.util.base.BaseViewModel
import com.myapp.kmmsample.usecase.CoinUseCase
import com.myapp.model.value.*
import com.myapp.presentation.extension.chartColor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Home画面 UIロジック
 *
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    coinUseCase: CoinUseCase
) : BaseViewModel<HomeContract.State, HomeContract.Effect, HomeContract.Event>() {

    init {
        val chartValues = coinUseCase.getAllAsset().map {
            ChartValue(
                it.type().label,
                it.value.toString(),
                coinUseCase.changeStableRate(it, LegalTender.JPY),
                it.type().chartColor()
            )
        }
        setState { copy(chartValue = chartValues) }
    }

    override fun initState(): HomeContract.State = HomeContract.State()

    override fun handleEvents(event: HomeContract.Event) {}

}

/**
 * Home画面 UI状態管理
 *
 */
interface HomeContract : BaseContract {

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