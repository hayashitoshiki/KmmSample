//
//  RepositoryComponentModule.swift
//  iosApp
//
//  Created by 林敏生 on 2022/05/15.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import domain

// RepositoryのDI定義
struct LocalCoinRepositoryFactoryKey : FactoryKey {

    static var value = {
        LocalCoinRepositoryImpl()
    }
}

// RepositoryのDI用のKey
extension FactoryContainer {
    var localCoinRepository: LocalCoinRepository {
        Self.resolve(LocalCoinRepositoryFactoryKey.self)
    }
}
