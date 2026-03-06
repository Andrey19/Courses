package ru.effectivemobile.courses.base.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.effectivemobile.courses.base.resources.StringResources
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ResourceBindsModule {
    @Binds
    @Singleton
    fun provideStringResources(impl: StringResourceImpl): StringResources
}