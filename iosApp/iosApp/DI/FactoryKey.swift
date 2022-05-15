//
//  FactoryKey.swift
//  iosApp
//
//  Created by 林敏生 on 2022/05/15.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

protocol FactoryKey {
    associatedtype Value

    static var value: (() -> Value) { get set }

}
