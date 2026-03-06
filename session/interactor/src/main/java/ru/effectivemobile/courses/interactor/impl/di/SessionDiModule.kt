package ru.effectivemobile.courses.interactor.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.effectivemobile.courses.interactor.session.repository.SessionRepository
import ru.effectivemobile.courses.interactor.session.source.SessionDataSource
import ru.effectivemobile.courses.interactor.session.usecase.LogInUseCase
import ru.effectivemobile.courses.interactor.session.usecase.LogOutUseCase
import ru.effectivemobile.courses.interactor.session.usecase.ObserveSessionInfoUseCase
import ru.effectivemobile.courses.interactor.impl.repository.SessionRepositoryImpl
import ru.effectivemobile.courses.interactor.impl.source.SessionDataSourceImpl
import ru.effectivemobile.courses.interactor.impl.usecase.LogInUseCaseImpl
import ru.effectivemobile.courses.interactor.impl.usecase.LogOutUseCaseImpl
import ru.effectivemobile.courses.interactor.impl.usecase.ObserveSessionInfoUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindLogInUseCaseImpl(
        useCaseImpl: LogInUseCaseImpl,
    ): LogInUseCase

    @Binds
    fun bindObserveSessionInfoUseCase(
        useCaseImpl: ObserveSessionInfoUseCaseImpl,
    ): ObserveSessionInfoUseCase

    @Binds
    fun bindLogOutUseCase(
        useCaseImpl: LogOutUseCaseImpl,
    ): LogOutUseCase
}

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun bindSessionRepository(
        repositoryImpl: SessionRepositoryImpl,
    ): SessionRepository
}

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {

    @Binds
    fun bindSessionDataSource(
        localSourceImpl: SessionDataSourceImpl,
    ): SessionDataSource

}
