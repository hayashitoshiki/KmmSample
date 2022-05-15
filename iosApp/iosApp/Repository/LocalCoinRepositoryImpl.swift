//
//  LocalCoinRepositoryImpl.swift
//  iosApp
//
//  Created by 林敏生 on 2022/05/15.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import domain


class LocalCoinRepositoryImpl : LocalCoinRepository {
    func getRateCoin() -> StepnCoinRate {
        return StepnCoinRate(
            gst: GstCoin(value: 10),
            gmt: GmtCoin(value: 20),
            sol: SolanaCoin(value: 30),
            usdc: UsdcCoin(value: 40),
            gem: GemAssets(value: 50),
            shoebox: ShoeboxAssets(value: 60),
            sneaker: SneakerAssets(value: 70)
        )
    }
    
    func getSpendingCoin() -> Spending {
        return Spending(
            gst: GstCoin(value: 10),
            gmt: GmtCoin(value: 20),
            sol: SolanaCoin(value: 30),
            gem: GemAssets(value: 40),
            shoebox: ShoeboxAssets(value: 50),
            sneaker: SneakerAssets(value: 60)
        )
    }
    
    func getWalletCoin() -> Wallet {
        return Wallet(
            gst: GstCoin(value: 10),
            gmt: GmtCoin(value: 20),
            sol: SolanaCoin(value: 30),
            usdc: UsdcCoin(value: 40),
            gem: GemAssets(value: 50),
            shoebox: ShoeboxAssets(value: 60),
            sneaker: SneakerAssets(value: 70)
        )
    }
    
    func updateRateGem(assets: GemAssets) { }
    
    func updateRateGmt(coin: GmtCoin) { }
    
    func updateRateGst(coin: GstCoin) { }
    
    func updateRateShoebox(assets: ShoeboxAssets) { }
    
    func updateRateSneaker(assets: SneakerAssets) { }
    
    func updateRateSol(coin: SolanaCoin) { }
    
    func updateRateUsdc(coin: UsdcCoin) { }
    
    func updateSpendingGem(assets: GemAssets) { }
    
    func updateSpendingGmt(coin: GmtCoin) { }
    
    func updateSpendingGst(coin: GstCoin) { }
    
    func updateSpendingShoebox(assets: ShoeboxAssets) { }
    
    func updateSpendingSneaker(assets: SneakerAssets) { }
    
    func updateSpendingSol(coin: SolanaCoin) { }
    
    func updateWalletGem(assets: GemAssets) { }
    
    func updateWalletGmt(coin: GmtCoin) { }
    
    func updateWalletGst(coin: GstCoin) { }
    
    func updateWalletShoebox(assets: ShoeboxAssets) { }
    
    func updateWalletSneaker(assets: SneakerAssets) { }
    
    func updateWalletSol(coin: SolanaCoin) { }
    
    func updateWalletUsdc(coin: UsdcCoin) { }
    
    
}
