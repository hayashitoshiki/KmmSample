package com.myapp.presentation.screen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import com.google.accompanist.pager.ExperimentalPagerApi
import com.myapp.component.component.Sample1TabComponent
import com.myapp.component.theme.Gray700
import com.myapp.component.theme.Green500
import com.myapp.model.value.RealAssetsType
import com.myapp.model.value.StepnCoinType
import com.myapp.ui.viewmodel.SettingContract
import com.myapp.ui.viewmodel.SettingViewModel

/**
 * 設定画面
 *
 */
@ExperimentalPagerApi
@Composable
fun SettingScreen(viewModel: SettingViewModel) {
    val state = viewModel.state.value
    val action: (SettingContract.Event) -> Unit = { viewModel.setEvent(it) }
    SettingContent(state, action)
}

@ExperimentalPagerApi
@Composable
private fun SettingContent(
    state: SettingContract.State,
    action: (SettingContract.Event) -> Unit
) {
    // 背景タップ設定
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus()
            })
        }
    ) {
        Sample1TabComponent(
            @Composable { SettingSpendingContent(state, action) },
            @Composable { SettingWalletContent(state, action) },
            @Composable { SettingRateContent(state, action) }
        )
    }
}

@Composable
private fun SettingWalletContent(
    state: SettingContract.State,
    action: (SettingContract.Event) -> Unit
) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        UpdateInputArea(
            label = StepnCoinType.GMT.label,
            value = state.walletGmt,
            enabled = state.enabledWalletGmt,
            onClickAction = { action(SettingContract.Event.OnUpdateWalletAssets(StepnCoinType.GMT)) },
            onTextChange = { action(SettingContract.Event.OnChangeWalletGmt(it)) }
        )
        UpdateInputArea(
            label = StepnCoinType.GST.label,
            value = state.walletGst,
            enabled = state.enabledWalletGst,
            onClickAction = { action(SettingContract.Event.OnUpdateWalletAssets(StepnCoinType.GST)) },
            onTextChange = { action(SettingContract.Event.OnChangeWalletGst(it)) }
        )
        UpdateInputArea(
            label = StepnCoinType.SOL.label,
            value = state.walletSol,
            enabled = state.enabledWalletSol,
            onClickAction = { action(SettingContract.Event.OnUpdateWalletAssets(StepnCoinType.SOL)) },
            onTextChange = { action(SettingContract.Event.OnChangeWalletSol(it)) }
        )
        UpdateInputArea(
            label = StepnCoinType.USCD.label,
            value = state.walletUsdc,
            enabled = state.enabledWalletUsdc,
            onClickAction = { action(SettingContract.Event.OnUpdateWalletAssets(StepnCoinType.USCD)) },
            onTextChange = { action(SettingContract.Event.OnChangeWalletUsdc(it)) }
        )
        UpdateInputArea(
            label = RealAssetsType.GEM.label,
            value = state.walletGem,
            enabled = state.enabledWalletGem,
            onClickAction = { action(SettingContract.Event.OnUpdateWalletAssets(RealAssetsType.GEM)) },
            onTextChange = { action(SettingContract.Event.OnChangeWalletGem(it)) }
        )
        UpdateInputArea(
            label = RealAssetsType.SHOEBOX.label,
            value = state.walletShoebox,
            enabled = state.enabledWalletSneaker,
            onClickAction = { action(SettingContract.Event.OnUpdateWalletAssets(RealAssetsType.SHOEBOX)) },
            onTextChange = { action(SettingContract.Event.OnChangeWalletShoebox(it)) }
        )
        UpdateInputArea(
            label = RealAssetsType.SNEAKER.label,
            value = state.walletSneaker,
            enabled = state.enabledWalletSneaker,
            onClickAction = { action(SettingContract.Event.OnUpdateWalletAssets(RealAssetsType.SNEAKER)) },
            onTextChange = { action(SettingContract.Event.OnChangeWalletSneaker(it)) }
        )
    }
}

@Composable
private fun SettingSpendingContent(
    state: SettingContract.State,
    action: (SettingContract.Event) -> Unit
) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        UpdateInputArea(
            label = StepnCoinType.GMT.label,
            value = state.spendingGmt,
            enabled = state.enabledSpendingGmt,
            onClickAction = { action(SettingContract.Event.OnUpdateSpendingAssets(StepnCoinType.GMT)) },
            onTextChange = { action(SettingContract.Event.OnChangeSpendingGmt(it)) }
        )
        UpdateInputArea(
            label = StepnCoinType.GST.label,
            value = state.spendingGst,
            enabled = state.enabledSpendingGst,
            onClickAction = { action(SettingContract.Event.OnUpdateSpendingAssets(StepnCoinType.GST)) },
            onTextChange = { action(SettingContract.Event.OnChangeSpendingGst(it)) }
        )
        UpdateInputArea(
            label = StepnCoinType.SOL.label,
            value = state.spendingSol,
            enabled = state.enabledSpendingSol,
            onClickAction = { action(SettingContract.Event.OnUpdateSpendingAssets(StepnCoinType.SOL)) },
            onTextChange = { action(SettingContract.Event.OnChangeSpendingSol(it)) }
        )
        UpdateInputArea(
            label = RealAssetsType.GEM.label,
            value = state.spendingGem,
            enabled = state.enabledSpendingGem,
            onClickAction = { action(SettingContract.Event.OnUpdateSpendingAssets(RealAssetsType.GEM)) },
            onTextChange = { action(SettingContract.Event.OnChangeSpendingGem(it)) }
        )
        UpdateInputArea(
            label = RealAssetsType.SHOEBOX.label,
            value = state.spendingShoebox,
            enabled = state.enabledSpendingShoebox,
            onClickAction = { action(SettingContract.Event.OnUpdateSpendingAssets(RealAssetsType.SHOEBOX)) },
            onTextChange = { action(SettingContract.Event.OnChangeSpendingShoebox(it)) }
        )
        UpdateInputArea(
            label = RealAssetsType.SNEAKER.label,
            value = state.spendingSneaker,
            enabled = state.enabledSpendingSneaker,
            onClickAction = { action(SettingContract.Event.OnUpdateSpendingAssets(RealAssetsType.SNEAKER)) },
            onTextChange = { action(SettingContract.Event.OnChangeSpendingSneaker(it)) }
        )
    }
}

@Composable
private fun SettingRateContent(
    state: SettingContract.State,
    action: (SettingContract.Event) -> Unit
) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        UpdateInputArea(
            label = StepnCoinType.GMT.label,
            value = state.rateGmt,
            enabled = state.enabledRateGmt,
            onClickAction = { action(SettingContract.Event.OnUpdateRateAssets(StepnCoinType.GMT)) },
            onTextChange = { action(SettingContract.Event.OnChangeRateGmt(it)) }
        )
        UpdateInputArea(
            label = StepnCoinType.GST.label,
            value = state.rateGst,
            enabled = state.enabledRateGst,
            onClickAction = { action(SettingContract.Event.OnUpdateRateAssets(StepnCoinType.GST)) },
            onTextChange = { action(SettingContract.Event.OnChangeRateGst(it)) }
        )
        UpdateInputArea(
            label = StepnCoinType.SOL.label,
            value = state.rateSol,
            enabled = state.enabledRateSol,
            onClickAction = { action(SettingContract.Event.OnUpdateRateAssets(StepnCoinType.SOL)) },
            onTextChange = { action(SettingContract.Event.OnChangeRateSol(it)) }
        )
        UpdateInputArea(
            label = StepnCoinType.USCD.label,
            value = state.rateUsdc,
            enabled = state.enabledRateUsdc,
            onClickAction = { action(SettingContract.Event.OnUpdateRateAssets(StepnCoinType.USCD)) },
            onTextChange = { action(SettingContract.Event.OnChangeRateUsdc(it)) }
        )
        UpdateInputArea(
            label = RealAssetsType.GEM.label,
            value = state.rateGem,
            enabled = state.enabledRateGem,
            onClickAction = { action(SettingContract.Event.OnUpdateRateAssets(RealAssetsType.GEM)) },
            onTextChange = { action(SettingContract.Event.OnChangeRateGem(it)) }
        )
        UpdateInputArea(
            label = RealAssetsType.SHOEBOX.label,
            value = state.rateShoebox,
            enabled = state.enabledRateShoebox,
            onClickAction = { action(SettingContract.Event.OnUpdateRateAssets(RealAssetsType.SHOEBOX)) },
            onTextChange = { action(SettingContract.Event.OnChangeRateShoebox(it)) }
        )
        UpdateInputArea(
            label = RealAssetsType.SNEAKER.label,
            value = state.rateSneaker,
            enabled = state.enabledRateSneaker,
            onClickAction = { action(SettingContract.Event.OnUpdateRateAssets(RealAssetsType.SNEAKER)) },
            onTextChange = { action(SettingContract.Event.OnChangeRateSneaker(it)) }
        )
    }
}

@Composable
fun UpdateInputArea(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    enabled: Boolean = true,
    onTextChange: (String) -> Unit,
    onClickAction: () -> Unit
) {

    val tint = if(enabled) { Green500 } else { Gray700 }
    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = value,
            onValueChange = { onTextChange(it) },
            label = { Text(label) },
        )
        IconButton(
            enabled = enabled,
            onClick = { onClickAction() }) {
            Icon(
                Icons.Filled.Refresh,
                "",
                tint = tint
            )
        }
    }
}
