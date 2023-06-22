package uz.gita.composeandfirebase.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap
import uz.gita.composeandfirebase.presentation.authscreen.login.LoginModel
import uz.gita.composeandfirebase.presentation.authscreen.signup.SignUpModel

/**
 *   Created by Jamik on 6/14/2023 ot 5:59 PM
 **/
@Module
@InstallIn(ActivityComponent::class)
abstract class HiltModule {

    @Binds
    @IntoMap
    @ScreenModelKey(LoginModel::class)
    abstract fun bindHiltLoginScreenModel(loginModel: LoginModel): ScreenModel


    @Binds
    @IntoMap
    @ScreenModelKey(SignUpModel::class)
    abstract fun bindHiltSignUpScreenModel(loginModel: SignUpModel): ScreenModel

}