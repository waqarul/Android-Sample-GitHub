package com.wtmcodex.androidsamplegithub.core.di.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wtmcodex.androidsamplegithub.BuildConfig
import com.wtmcodex.androidsamplegithub.constants.APIConstants
import com.wtmcodex.androidsamplegithub.core.data.source.remote.retrofit.GitHubRepositoryApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .readTimeout(APIConstants.READ_TIME_OUT_DELAY, TimeUnit.SECONDS)
            .connectTimeout(APIConstants.CONNECT_TIME_OUT_DELAY, TimeUnit.SECONDS)
            .addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)).build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        var level: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.NONE
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return HttpLoggingInterceptor().setLevel(level)
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(
        client: OkHttpClient?,
        context: Context?
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(APIConstants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGitHubRepositoryApiService(retrofit: Retrofit): GitHubRepositoryApiService {
        return retrofit.create(GitHubRepositoryApiService::class.java)
    }
}