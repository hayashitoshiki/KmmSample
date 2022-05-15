//
//  Injeted.swift
//  iosApp
//
//  Created by 林敏生 on 2022/05/15.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

@propertyWrapper
struct Injected<T> {
    var wrappedValue: T
    
    init(_ keyPath: KeyPath<FactoryContainer, T>) {
        wrappedValue = FactoryContainer[keyPath]
    }
}
