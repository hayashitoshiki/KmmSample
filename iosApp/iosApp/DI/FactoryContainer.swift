//
//  FactoryContainer.swift
//  iosApp
//
//  Created by 林敏生 on 2022/05/15.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

struct FactoryContainer {

    static subscript<T>(_ keyPath: KeyPath<FactoryContainer, T>) -> T {
        container[keyPath: keyPath]
    }

    static func register<K>(_ key: K.Type, _ value: @escaping () -> K.Value) where K: FactoryKey {
        key.value = value
    }

    static func resolve<K>(_ key: K.Type) -> K.Value where K: FactoryKey {
        key.value()
    }

    private static var container = FactoryContainer()

}


