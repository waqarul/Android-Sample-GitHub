package com.wtmcodex.androidsamplegithub.core.di.module

import com.wtmcodex.androidsamplegithub.core.di.scope.DefaultDispatcher
import com.wtmcodex.androidsamplegithub.core.di.scope.IoDispatcher
import com.wtmcodex.androidsamplegithub.core.di.scope.MainDispatcher
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


@Module
object DispatcherModule {

    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @IoDispatcher
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDispatcher
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
