// Generated by Dagger (https://dagger.dev).
package com.myapp.kmmsample.usecase;

import com.myapp.kmmsample.repository.LocalCoinRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class CoinUseCaseImpl_Factory implements Factory<CoinUseCaseImpl> {
  private final Provider<LocalCoinRepository> localCoinRepositoryProvider;

  public CoinUseCaseImpl_Factory(Provider<LocalCoinRepository> localCoinRepositoryProvider) {
    this.localCoinRepositoryProvider = localCoinRepositoryProvider;
  }

  @Override
  public CoinUseCaseImpl get() {
    return newInstance(localCoinRepositoryProvider.get());
  }

  public static CoinUseCaseImpl_Factory create(
      Provider<LocalCoinRepository> localCoinRepositoryProvider) {
    return new CoinUseCaseImpl_Factory(localCoinRepositoryProvider);
  }

  public static CoinUseCaseImpl newInstance(LocalCoinRepository localCoinRepository) {
    return new CoinUseCaseImpl(localCoinRepository);
  }
}