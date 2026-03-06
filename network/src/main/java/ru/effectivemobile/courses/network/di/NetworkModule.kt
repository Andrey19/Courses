package ru.effectivemobile.courses.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.effectivemobile.courses.network.connection.impl.loggingInterceptor
import ru.effectivemobile.courses.network.connection.impl.okhttp
import javax.inject.Singleton

const val BASE_URL = "https://drive.usercontent.google.com/u/0/"

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = okhttp(loggingInterceptor())

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

}