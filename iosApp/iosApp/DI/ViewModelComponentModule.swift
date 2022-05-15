//
//  ViewModelComponentModule.swift
//  iosApp
//
//  Created by 林敏生 on 2022/05/15.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import domain

// HomeViewModelのDI定義
struct HomeViewModelFactoryKey : FactoryKey {

    @Injected(\.coinUseCase) static var coinUseCase: CoinUseCase

    static var value = {
        HomeViewModel(coinUseCase: coinUseCase)
    }
}

// ViewModelのDI用のKey
extension FactoryContainer {
    var homeViewModel: HomeViewModel {
        Self.resolve(HomeViewModelFactoryKey.self)
    }
}
