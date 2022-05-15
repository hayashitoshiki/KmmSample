//
//  HomeViewModel.swift
//  iosApp
//
//  Created by 林敏生 on 2022/05/15.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import domain


class HomeViewModel {

    private var coinUseCase : CoinUseCase
    var wallet : Wallet

    init(coinUseCase : CoinUseCase) {
        self.coinUseCase = coinUseCase
        wallet = coinUseCase.getWalletCoin()
    }
}
