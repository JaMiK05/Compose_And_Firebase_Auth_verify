package uz.gita.composeandfirebase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import uz.gita.composeandfirebase.presentation.authscreen.login.LoginDirection
import uz.gita.composeandfirebase.presentation.authscreen.login.LoginDirectionImpl
import uz.gita.composeandfirebase.presentation.authscreen.signup.SignUpDirection
import uz.gita.composeandfirebase.presentation.authscreen.signup.SignUpDirectionImpl
import javax.inject.Singleton

/**
 *   Created by Jamik on 6/14/2023 ot 5:44 PM
 **/

@Module
@InstallIn(ActivityComponent::class)
interface DirectionModule {

    @[Binds]
    fun loginDirection(impl: LoginDirectionImpl): LoginDirection

    @Binds
    fun signUpDirection(impl: SignUpDirectionImpl): SignUpDirection


}