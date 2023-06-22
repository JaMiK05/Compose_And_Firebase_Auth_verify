package uz.gita.composeandfirebase.presentation.authscreen.login

import uz.gita.composeandfirebase.presentation.authscreen.signup.SignUpScreen
import uz.gita.composeandfirebase.presentation.homescreen.home.HomeScreen
import uz.gita.composeandfirebase.util.navigation.AppNavigator
import javax.inject.Inject

interface LoginDirection {
    suspend fun openHomeScreen()
    suspend fun openSignUpScreen()
}

class LoginDirectionImpl @Inject constructor(
    private val navigator: AppNavigator,
) : LoginDirection {
    override suspend fun openHomeScreen() {
        navigator.replace(HomeScreen())
    }

    override suspend fun openSignUpScreen() {
        navigator.navigateTo(SignUpScreen())
    }
}