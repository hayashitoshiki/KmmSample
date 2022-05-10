package com.myapp.kmmsample.usecase;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/myapp/kmmsample/usecase/CoinUseCaseImpl;", "Lcom/myapp/kmmsample/usecase/CoinUseCase;", "localCoinRepository", "Lcom/myapp/kmmsample/repository/LocalCoinRepository;", "(Lcom/myapp/kmmsample/repository/LocalCoinRepository;)V", "getRateCoin", "Lcom/myapp/model/entity/StepnCoinRate;", "getSpendingCoin", "Lcom/myapp/model/entity/Spending;", "getWalletCoin", "Lcom/myapp/model/entity/Wallet;", "updateRateAssets", "", "assets", "Lcom/myapp/model/value/Assets;", "updateSpendingAssets", "updateWalletAssets", "domain_debug"})
public final class CoinUseCaseImpl implements com.myapp.kmmsample.usecase.CoinUseCase {
    private final com.myapp.kmmsample.repository.LocalCoinRepository localCoinRepository = null;
    
    @javax.inject.Inject()
    public CoinUseCaseImpl(@org.jetbrains.annotations.NotNull()
    com.myapp.kmmsample.repository.LocalCoinRepository localCoinRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.myapp.model.entity.Wallet getWalletCoin() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.myapp.model.entity.Spending getSpendingCoin() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.myapp.model.entity.StepnCoinRate getRateCoin() {
        return null;
    }
    
    @java.lang.Override()
    public void updateWalletAssets(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.Assets assets) {
    }
    
    @java.lang.Override()
    public void updateSpendingAssets(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.Assets assets) {
    }
    
    @java.lang.Override()
    public void updateRateAssets(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.Assets assets) {
    }
}