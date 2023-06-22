package uz.gita.composeandfirebase.di

import cafe.adriel.voyager.core.model.ScreenModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.composeandfirebase.domain.repositories.authrepository.AuthRepository
import uz.gita.composeandfirebase.domain.repositories.authrepository.impl.AuthRepositoryImpl
import javax.inject.Singleton

/**
 *   Created by Jamik on 6/14/2023 ot 5:09 PM
 **/

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun authRepositoryBind(impl: AuthRepositoryImpl): AuthRepository

}