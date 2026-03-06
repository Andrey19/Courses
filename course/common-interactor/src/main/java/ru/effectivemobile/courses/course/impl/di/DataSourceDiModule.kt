package ru.effectivemobile.courses.course.impl.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.effectivemobile.courses.course.api.repository.CoursesRepository
import ru.effectivemobile.courses.course.api.source.CourseLocalDataSource
import ru.effectivemobile.courses.course.api.source.CourseRemoteDataSource
import ru.effectivemobile.courses.course.api.usecase.ChangeCourseIsLikedUseCase
import ru.effectivemobile.courses.course.api.usecase.ObserveCoursesUseCase
import ru.effectivemobile.courses.course.api.usecase.ObserveLikedCoursesUseCase
import ru.effectivemobile.courses.course.api.usecase.ObserveNetworkStatusUseCase
import ru.effectivemobile.courses.course.impl.repository.CourseRepositoryImpl
import ru.effectivemobile.courses.course.impl.source.local.CourseLocalDataSourceImpl
import ru.effectivemobile.courses.course.impl.source.remote.CourseRemoteDataSourceImpl
import ru.effectivemobile.courses.course.impl.source.remote.CoursesApiService
import ru.effectivemobile.courses.course.impl.usecase.ChangeCourseIsLikedUseCaseImpl
import ru.effectivemobile.courses.course.impl.usecase.ObserveCoursesUseCaseImpl
import ru.effectivemobile.courses.course.impl.usecase.ObserveLikedCoursesUseCaseImpl
import ru.effectivemobile.courses.course.impl.usecase.ObserveNetworkStatusUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCaseModule {
    @Binds
    fun bindChangeCourseIsLikedUseCase(
        useCaseImpl: ChangeCourseIsLikedUseCaseImpl,
    ): ChangeCourseIsLikedUseCase

    @Binds
    fun bindObserveFavouriteCoursesUseCase(
        useCaseImpl: ObserveLikedCoursesUseCaseImpl,
    ): ObserveLikedCoursesUseCase

    @Binds
    fun bindObserveCoursesUseCase(
        useCaseImpl: ObserveCoursesUseCaseImpl,
    ): ObserveCoursesUseCase

    @Binds
    fun bindObserveNetworkExchangeStatusUseCase(
        useCaseImpl: ObserveNetworkStatusUseCaseImpl,
    ): ObserveNetworkStatusUseCase
}

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun bindCourseRepository(
        repositoryImpl: CourseRepositoryImpl,
    ): CoursesRepository
}

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {

    @Binds
    fun bindCourseLocalDataSource(
        localSourceImpl: CourseLocalDataSourceImpl,
    ): CourseLocalDataSource

    @Binds
    fun bindCourseRemoteDataSource(
        remoteSourceImpl: CourseRemoteDataSourceImpl,
    ): CourseRemoteDataSource
}
@Module
@InstallIn(SingletonComponent::class)
internal object ApiServiceModule {
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CoursesApiService {
        return retrofit.create(CoursesApiService::class.java)
    }
}