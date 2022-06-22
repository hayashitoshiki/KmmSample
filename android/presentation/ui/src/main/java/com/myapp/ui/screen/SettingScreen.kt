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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import com.google.accompanist.pager.ExperimentalPagerApi
import com.myapp.component.component.Sample1TabComponent
import com.myapp.component.theme.Gray700
import com.myapp.component.theme.Green500
import com.myapp.model.value.RealAssetsType
import com.myapp.model.value.StepnCoinType
import com.myapp.ui.util.LayoutTag
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
            onTextChange = { action(SettingContract.Event.OnChangeWalletGmt(it)) },
            inputFieldName = SettingScreenItemTag.InputWalletGmt,
            updateButtonName = SettingScreenItemTag.BtnWalletGmt
        )
        UpdateInputArea(
            label = StepnCoinType.GST.label,
            value = state.walletGst,
            enabled = state.enabledWalletGst,
            onClickAction = { action(SettingContract.Event.OnUpdateWalletAssets(StepnCoinType.GST)) },
            onTextChange = { action(SettingContract.Event.OnChangeWalletGst(it)) },
            inputFieldName = SettingScreenItemTag.InputWalletGst,
            updateButtonName = SettingScreenItemTag.BtnWalletGst
        )
        UpdateInputArea(
            label = StepnCoinType.SOL.label,
            value = state.walletSol,
            enabled = state.enabledWalletSol,
            onClickAction = { action(SettingContract.Event.OnUpdateWalletAssets(StepnCoinType.SOL)) },
            onTextChange = { action(SettingContract.Event.OnChangeWalletSol(it)) },
            inputFieldName = SettingScreenItemTag.InputWalletSol,
            updateButtonName = SettingScreenItemTag.BtnWalletSol
        )
        UpdateInputArea(
            label = StepnCoinType.USCD.label,
            value = state.walletUsdc,
            enabled = state.enabledWalletUsdc,
            onClickAction = { action(SettingContract.Event.OnUpdateWalletAssets(StepnCoinType.USCD)) },
            onTextChange = { action(SettingContract.Event.OnChangeWalletUsdc(it)) },
            inputFieldName = SettingScreenItemTag.InputWalletUsdc,
            updateButtonName = SettingScreenItemTag.BtnWalletUsdc
        )
        UpdateInputArea(
            label = RealAssetsType.GEM.label,
            value = state.walletGem,
            enabled = state.enabledWalletGem,
            onClickAction = { action(SettingContract.Event.OnUpdateWalletAssets(RealAssetsType.GEM)) },
            onTextChange = { action(SettingContract.Event.OnChangeWalletGem(it)) },
            inputFieldName = SettingScreenItemTag.InputWalletGem,
            updateButtonName = SettingScreenItemTag.BtnWalletGem
        )
        UpdateInputArea(
            label = RealAssetsType.SHOEBOX.label,
            value = state.walletShoebox,
            enabled = state.enabledWalletSneaker,
            onClickAction = { action(SettingContract.Event.OnUpdateWalletAssets(RealAssetsType.SHOEBOX)) },
            onTextChange = { action(SettingContract.Event.OnChangeWalletShoebox(it)) },
            inputFieldName = SettingScreenItemTag.InputWalletShoebox,
            updateButtonName = SettingScreenItemTag.BtnWalletShoebox
        )
        UpdateInputArea(
            label = RealAssetsType.SNEAKER.label,
            value = state.walletSneaker,
            enabled = state.enabledWalletSneaker,
            onClickAction = { action(SettingContract.Event.OnUpdateWalletAssets(RealAssetsType.SNEAKER)) },
            onTextChange = { action(SettingContract.Event.OnChangeWalletSneaker(it)) },
            inputFieldName = SettingScreenItemTag.InputWalletSneaker,
            updateButtonName = SettingScreenItemTag.BtnWalletSneaker
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
            onTextChange = { action(SettingContract.Event.OnChangeSpendingGmt(it)) },
            inputFieldName = SettingScreenItemTag.InputSpendingGmt,
            updateButtonName = SettingScreenItemTag.BtnSpendingGmt
        )
        UpdateInputArea(
            label = StepnCoinType.GST.label,
            value = state.spendingGst,
            enabled = state.enabledSpendingGst,
            onClickAction = { action(SettingContract.Event.OnUpdateSpendingAssets(StepnCoinType.GST)) },
            onTextChange = { action(SettingContract.Event.OnChangeSpendingGst(it)) },
            inputFieldName = SettingScreenItemTag.InputSpendingGst,
            updateButtonName = SettingScreenItemTag.BtnSpendingGst
        )
        UpdateInputArea(
            label = StepnCoinType.SOL.label,
            value = state.spendingSol,
            enabled = state.enabledSpendingSol,
            onClickAction = { action(SettingContract.Event.OnUpdateSpendingAssets(StepnCoinType.SOL)) },
            onTextChange = { action(SettingContract.Event.OnChangeSpendingSol(it)) },
            inputFieldName = SettingScreenItemTag.InputSpendingSol,
            updateButtonName = SettingScreenItemTag.BtnSpendingSol
        )
        UpdateInputArea(
            label = RealAssetsType.GEM.label,
            value = state.spendingGem,
            enabled = state.enabledSpendingGem,
            onClickAction = { action(SettingContract.Event.OnUpdateSpendingAssets(RealAssetsType.GEM)) },
            onTextChange = { action(SettingContract.Event.OnChangeSpendingGem(it)) },
            inputFieldName = SettingScreenItemTag.InputSpendingGem,
            updateButtonName = SettingScreenItemTag.BtnSpendingGem
        )
        UpdateInputArea(
            label = RealAssetsType.SHOEBOX.label,
            value = state.spendingShoebox,
            enabled = state.enabledSpendingShoebox,
            onClickAction = { action(SettingContract.Event.OnUpdateSpendingAssets(RealAssetsType.SHOEBOX)) },
            onTextChange = { action(SettingContract.Event.OnChangeSpendingShoebox(it)) },
            inputFieldName = SettingScreenItemTag.InputSpendingShoebox,
            updateButtonName = SettingScreenItemTag.BtnSpendingShoebox
        )
        UpdateInputArea(
            label = RealAssetsType.SNEAKER.label,
            value = state.spendingSneaker,
            enabled = state.enabledSpendingSneaker,
            onClickAction = { action(SettingContract.Event.OnUpdateSpendingAssets(RealAssetsType.SNEAKER)) },
            onTextChange = { action(SettingContract.Event.OnChangeSpendingSneaker(it)) },
            inputFieldName = SettingScreenItemTag.InputSpendingSneaker,
            updateButtonName = SettingScreenItemTag.BtnSpendingSneaker
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
            onTextChange = { action(SettingContract.Event.OnChangeRateGmt(it)) },
            inputFieldName = SettingScreenItemTag.InputRateGmt,
            updateButtonName = SettingScreenItemTag.BtnRateGmt
        )
        UpdateInputArea(
            label = StepnCoinType.GST.label,
            value = state.rateGst,
            enabled = state.enabledRateGst,
            onClickAction = { action(SettingContract.Event.OnUpdateRateAssets(StepnCoinType.GST)) },
            onTextChange = { action(SettingContract.Event.OnChangeRateGst(it)) },
            inputFieldName = SettingScreenItemTag.InputRateGst,
            updateButtonName = SettingScreenItemTag.BtnRateGst
        )
        UpdateInputArea(
            label = StepnCoinType.SOL.label,
            value = state.rateSol,
            enabled = state.enabledRateSol,
            onClickAction = { action(SettingContract.Event.OnUpdateRateAssets(StepnCoinType.SOL)) },
            onTextChange = { action(SettingContract.Event.OnChangeRateSol(it)) },
            inputFieldName = SettingScreenItemTag.InputRateSol,
            updateButtonName = SettingScreenItemTag.BtnRateSol
        )
        UpdateInputArea(
            label = StepnCoinType.USCD.label,
            value = state.rateUsdc,
            enabled = state.enabledRateUsdc,
            onClickAction = { action(SettingContract.Event.OnUpdateRateAssets(StepnCoinType.USCD)) },
            onTextChange = { action(SettingContract.Event.OnChangeRateUsdc(it)) },
            inputFieldName = SettingScreenItemTag.InputRateUsdc,
            updateButtonName = SettingScreenItemTag.BtnRateUsdc
        )
        UpdateInputArea(
            label = RealAssetsType.GEM.label,
            value = state.rateGem,
            enabled = state.enabledRateGem,
            onClickAction = { action(SettingContract.Event.OnUpdateRateAssets(RealAssetsType.GEM)) },
            onTextChange = { action(SettingContract.Event.OnChangeRateGem(it)) },
            inputFieldName = SettingScreenItemTag.InputRateGem,
            updateButtonName = SettingScreenItemTag.BtnRateGem
        )
        UpdateInputArea(
            label = RealAssetsType.SHOEBOX.label,
            value = state.rateShoebox,
            enabled = state.enabledRateShoebox,
            onClickAction = { action(SettingContract.Event.OnUpdateRateAssets(RealAssetsType.SHOEBOX)) },
            onTextChange = { action(SettingContract.Event.OnChangeRateShoebox(it)) },
            inputFieldName = SettingScreenItemTag.InputRateShoebox,
            updateButtonName = SettingScreenItemTag.BtnRateShoebox
        )
        UpdateInputArea(
            label = RealAssetsType.SNEAKER.label,
            value = state.rateSneaker,
            enabled = state.enabledRateSneaker,
            onClickAction = { action(SettingContract.Event.OnUpdateRateAssets(RealAssetsType.SNEAKER)) },
            onTextChange = { action(SettingContract.Event.OnChangeRateSneaker(it)) },
            inputFieldName = SettingScreenItemTag.InputRateSneaker,
            updateButtonName = SettingScreenItemTag.BtnRateSneaker
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
    onClickAction: () -> Unit,
    inputFieldName: LayoutTag,
    updateButtonName: LayoutTag
) {

    val tint = if(enabled) { Green500 } else { Gray700 }
    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            modifier = Modifier.testTag(inputFieldName.value),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = value,
            onValueChange = { onTextChange(it) },
            label = { Text(label) },
        )
        IconButton(
            modifier = Modifier.testTag(updateButtonName.value),
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

/**
 * 設定画面UIパーツ一覧
 *
 */
sealed class SettingScreenItemTag(value: String) : LayoutTag(value) {

    // inputField
    object InputWalletGmt : SettingScreenItemTag("InputWalletGmt")
    object InputWalletGst : SettingScreenItemTag("InputWalletGst")
    object InputWalletSol : SettingScreenItemTag("InputWalletSol")
    object InputWalletUsdc : SettingScreenItemTag("InputWalletUsdc")
    object InputWalletGem : SettingScreenItemTag("InputWalletGem")
    object InputWalletShoebox : SettingScreenItemTag("InputWalletShoebox")
    object InputWalletSneaker : SettingScreenItemTag("InputWalletSneaker")
    object InputSpendingGmt : SettingScreenItemTag("InputSpendingGmt")
    object InputSpendingGst : SettingScreenItemTag("InputSpendingGst")
    object InputSpendingSol : SettingScreenItemTag("InputSpendingSol")
    object InputSpendingGem : SettingScreenItemTag("InputSpendingGem")
    object InputSpendingShoebox : SettingScreenItemTag("InputSpendingShoebox")
    object InputSpendingSneaker : SettingScreenItemTag("InputSpendingSneaker")
    object InputRateGmt : SettingScreenItemTag("InputRateGmt")
    object InputRateGst : SettingScreenItemTag("InputRateGst")
    object InputRateSol : SettingScreenItemTag("InputRateSol")
    object InputRateUsdc : SettingScreenItemTag("InputRateUsdc")
    object InputRateGem : SettingScreenItemTag("InputRateGem")
    object InputRateShoebox : SettingScreenItemTag("InputRateShoebox")
    object InputRateSneaker : SettingScreenItemTag("InputRateSneaker")

    // button
    object BtnWalletGmt : SettingScreenItemTag("BtnWalletGmt")
    object BtnWalletGst : SettingScreenItemTag("BtnWalletGst")
    object BtnWalletSol : SettingScreenItemTag("BtnWalletSol")
    object BtnWalletUsdc : SettingScreenItemTag("BtnWalletUsdc")
    object BtnWalletGem : SettingScreenItemTag("BtnWalletGem")
    object BtnWalletShoebox : SettingScreenItemTag("BtnWalletShoebox")
    object BtnWalletSneaker : SettingScreenItemTag("BtnWalletSneaker")
    object BtnSpendingGmt : SettingScreenItemTag("BtnSpendingGmt")
    object BtnSpendingGst : SettingScreenItemTag("BtnSpendingGst")
    object BtnSpendingSol : SettingScreenItemTag("BtnSpendingSol")
    object BtnSpendingGem : SettingScreenItemTag("BtnSpendingGem")
    object BtnSpendingShoebox : SettingScreenItemTag("BtnSpendingShoebox")
    object BtnSpendingSneaker : SettingScreenItemTag("BtnSpendingSneaker")
    object BtnRateGmt : SettingScreenItemTag("BtnRateGmt")
    object BtnRateGst : SettingScreenItemTag("BtnRateGst")
    object BtnRateSol : SettingScreenItemTag("BtnRateSol")
    object BtnRateUsdc : SettingScreenItemTag("BtnRateUsdc")
    object BtnRateGem : SettingScreenItemTag("BtnRateGem")
    object BtnRateShoebox : SettingScreenItemTag("BtnRateShoebox")
    object BtnRateSneaker : SettingScreenItemTag("BtnRateSneaker")
}
