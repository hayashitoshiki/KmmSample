package com.myapp.kmmsample.usecase

import com.myapp.kmmsample.repository.LocalCoinRepository
import com.myapp.model.entity.Spending
import com.myapp.model.entity.StepnCoinRate
import com.myapp.model.entity.Wallet
import com.myapp.model.value.*


actual class CoinUseCaseImpl actual constructor(
    actual val localCoinRepository: LocalCoinRepository
) : CoinUseCase {

    actual override fun getWalletCoin(): Wallet {
        return localCoinRepository.getWalletCoin()
    }

    actual override fun getSpendingCoin(): Spending {
        return localCoinRepository.getSpendingCoin()
    }

    actual override fun getRateCoin(): StepnCoinRate {
        return localCoinRepository.getRateCoin()
    }

    actual override fun updateWalletAssets(assets: Assets) {
        when(assets) {
            is GmtCoin -> localCoinRepository.updateWalletGmt(assets)
            is GstCoin -> localCoinRepository.updateWalletGst(assets)
            is SolanaCoin -> localCoinRepository.updateWalletSol(assets)
            is UsdcCoin -> localCoinRepository.updateWalletUsdc(assets)
            is GemAssets -> localCoinRepository.updateWalletGem(assets)
            is ShoeboxAssets -> localCoinRepository.updateWalletShoebox(assets)
            is SneakerAssets -> localCoinRepository.updateWalletSneaker(assets)
        }
    }

    actual override fun updateSpendingAssets(assets: Assets) {
        when(assets) {
            is GmtCoin -> localCoinRepository.updateSpendingGmt(assets)
            is GstCoin -> localCoinRepository.updateSpendingGst(assets)
            is SolanaCoin -> localCoinRepository.updateSpendingSol(assets)
            is GemAssets -> localCoinRepository.updateSpendingGem(assets)
            is ShoeboxAssets -> localCoinRepository.updateSpendingShoebox(assets)
            is SneakerAssets -> localCoinRepository.updateSpendingSneaker(assets)
        }
    }

    actual override fun updateRateAssets(assets: Assets) {
        when(assets) {
            is GmtCoin -> localCoinRepository.updateRateGmt(assets)
            is GstCoin -> localCoinRepository.updateRateGst(assets)
            is SolanaCoin -> localCoinRepository.updateRateSol(assets)
            is UsdcCoin -> localCoinRepository.updateRateUsdc(assets)
            is GemAssets -> localCoinRepository.updateRateGem(assets)
            is ShoeboxAssets -> localCoinRepository.updateRateShoebox(assets)
            is SneakerAssets -> localCoinRepository.updateRateSneaker(assets)
        }
    }
}