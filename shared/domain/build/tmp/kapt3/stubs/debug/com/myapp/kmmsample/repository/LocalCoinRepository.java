package com.myapp.kmmsample.repository;

import java.lang.System;

/**
 * STEP'Nのコインに関するローカルCRUD処理
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0010H&J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0012H&J\u0010\u0010\u0013\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0014H&J\u0010\u0010\u0015\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0016H&J\u0010\u0010\u0017\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0018H&J\u0010\u0010\u0019\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u001b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0010H&J\u0010\u0010\u001c\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0012H&J\u0010\u0010\u001d\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0014H&J\u0010\u0010\u001e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0016H&J\u0010\u0010\u001f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010 \u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH&J\u0010\u0010!\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0010H&J\u0010\u0010\"\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0012H&J\u0010\u0010#\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0014H&J\u0010\u0010$\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0016H&J\u0010\u0010%\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0018H&\u00a8\u0006&"}, d2 = {"Lcom/myapp/kmmsample/repository/LocalCoinRepository;", "", "getRateCoin", "Lcom/myapp/model/entity/StepnCoinRate;", "getSpendingCoin", "Lcom/myapp/model/entity/Spending;", "getWalletCoin", "Lcom/myapp/model/entity/Wallet;", "updateRateGem", "", "assets", "Lcom/myapp/model/value/GemAssets;", "updateRateGmt", "coin", "Lcom/myapp/model/value/GmtCoin;", "updateRateGst", "Lcom/myapp/model/value/GstCoin;", "updateRateShoebox", "Lcom/myapp/model/value/ShoeboxAssets;", "updateRateSneaker", "Lcom/myapp/model/value/SneakerAssets;", "updateRateSol", "Lcom/myapp/model/value/SolanaCoin;", "updateRateUsdc", "Lcom/myapp/model/value/UsdcCoin;", "updateSpendingGem", "updateSpendingGmt", "updateSpendingGst", "updateSpendingShoebox", "updateSpendingSneaker", "updateSpendingSol", "updateWalletGem", "updateWalletGmt", "updateWalletGst", "updateWalletShoebox", "updateWalletSneaker", "updateWalletSol", "updateWalletUsdc", "domain_debug"})
public abstract interface LocalCoinRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.myapp.model.entity.Wallet getWalletCoin();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.myapp.model.entity.Spending getSpendingCoin();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.myapp.model.entity.StepnCoinRate getRateCoin();
    
    public abstract void updateWalletGmt(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.GmtCoin coin);
    
    public abstract void updateWalletGst(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.GstCoin coin);
    
    public abstract void updateWalletSol(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.SolanaCoin coin);
    
    public abstract void updateWalletUsdc(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.UsdcCoin coin);
    
    public abstract void updateWalletGem(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.GemAssets assets);
    
    public abstract void updateWalletShoebox(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.ShoeboxAssets assets);
    
    public abstract void updateWalletSneaker(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.SneakerAssets assets);
    
    public abstract void updateRateGmt(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.GmtCoin coin);
    
    public abstract void updateRateGst(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.GstCoin coin);
    
    public abstract void updateRateSol(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.SolanaCoin coin);
    
    public abstract void updateRateUsdc(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.UsdcCoin coin);
    
    public abstract void updateRateGem(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.GemAssets assets);
    
    public abstract void updateRateShoebox(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.ShoeboxAssets assets);
    
    public abstract void updateRateSneaker(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.SneakerAssets assets);
    
    public abstract void updateSpendingGmt(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.GmtCoin coin);
    
    public abstract void updateSpendingGst(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.GstCoin coin);
    
    public abstract void updateSpendingSol(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.SolanaCoin coin);
    
    public abstract void updateSpendingGem(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.GemAssets assets);
    
    public abstract void updateSpendingShoebox(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.ShoeboxAssets assets);
    
    public abstract void updateSpendingSneaker(@org.jetbrains.annotations.NotNull()
    com.myapp.model.value.SneakerAssets assets);
}