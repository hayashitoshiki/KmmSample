package com.myapp.kmmsample.usecase;

import java.lang.System;

/**
 * STEP'Nのコインに関する業務ロジック
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&\u00a8\u0006\u000e"}, d2 = {"Lcom/myapp/kmmsample/usecase/CoinUseCase;", "", "getRateCoin", "Lcom/myapp/model/entity/StepnCoinRate;", "getSpendingCoin", "Lcom/myapp/model/entity/Spending;", "getWalletCoin", "Lcom/myapp/model/entity/Wallet;", "updateRateAssets", "", "assets", "Lcom/myapp/model/value/Assets;", "updateSpendingAssets", "updateWalletAssets", "domain_debug"})
public abstract interface CoinUseCase {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.myapp.model.entity.Wallet getWalletCoin();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.myapp.model.entity.Spending getSpendingCoin();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.myapp.model.entity.StepnCoinRate getRateCoin();
    
    public abstract void updateWalletAssets(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.Assets assets);
    
    public abstract void updateSpendingAssets(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.Assets assets);
    
    public abstract void updateRateAssets(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.Assets assets);
}