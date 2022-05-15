//
//  HomeScreen.swift
//  iosApp
//
//  Created by 林敏生 on 2022/05/15.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct HomeScreen: View {
    @Injected(\.homeViewModel) var viewModel: HomeViewModel

    var body: some View {
        VStack() {
            Text("Wallet 残高")
            Text("gmt = " + String(viewModel.wallet.gmt.value))
            Text("gst = " + String(viewModel.wallet.gst.value))
            Text("sol = " + String(viewModel.wallet.sol.value))
            Text("shoebox = " + String(viewModel.wallet.shoebox.value))
            Text("gem = " + String(viewModel.wallet.gem.value))
            Text("sneaker = " + String(viewModel.wallet.sneaker.value))
        }
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
