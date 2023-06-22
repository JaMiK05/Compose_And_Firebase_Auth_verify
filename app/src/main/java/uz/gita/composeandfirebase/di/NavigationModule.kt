package uz.gita.composeandfirebase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.composeandfirebase.util.navigation.AppNavigator
import uz.gita.composeandfirebase.util.navigation.NavigationDispatcher
import uz.gita.composeandfirebase.util.navigation.NavigationHandler
import javax.inject.Singleton

/**
 *   Created by Jamik on 6/14/2023 ot 5:20 PM
 **/

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @[Binds Singleton]
    fun bindAppNavigator(impl: NavigationDispatcher): AppNavigator

    @[Binds Singleton]
    fun bindNavigationHandler(impl: NavigationDispatcher): NavigationHandler

}