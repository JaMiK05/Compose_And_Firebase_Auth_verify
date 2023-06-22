package uz.gita.composeandfirebase.di

import android.content.Context
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 *   Created by Jamik on 6/15/2023 ot 1:04 PM
 **/
@Module
@InstallIn(ActivityComponent::class)
interface ContextModule {

    @Binds
    fun contextBind(@ApplicationContext context: Context):Context

}