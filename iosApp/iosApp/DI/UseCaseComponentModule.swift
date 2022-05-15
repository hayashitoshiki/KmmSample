//
//  UseCaseComponentModule.swift
//  iosApp
//
//  Created by 林敏生 on 2022/05/15.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import domain

// RepositoryのDI定義
struct CoinUseCaseFactoryKey : FactoryKey {

    @Injected(\.localCoinRepository) static var localCoinRepository: LocalCoinRepository

    static var value = {
        CoinUseCaseImpl(localCoinRepository: localCoinRepository)
    }
}

// RepositoryのDI用のKey
extension FactoryContainer {
    var coinUseCase: CoinUseCase {
        Self.resolve(CoinUseCaseFactoryKey.self)
    }
}
