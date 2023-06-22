package uz.gita.composeandfirebase.presentation.authscreen.signup

import uz.gita.composeandfirebase.presentation.homescreen.home.HomeScreen
import uz.gita.composeandfirebase.util.navigation.AppNavigator
import javax.inject.Inject

/**
 *   Created by Jamik on 6/14/2023 ot 6:56 PM
 **/

interface SignUpDirection {
    suspend fun openMainScreen()
    suspend fun popBackStack()
}

class SignUpDirectionImpl @Inject constructor(
    private val navigator: AppNavigator,
) : SignUpDirection {
    override suspend fun openMainScreen() {
        navigator.replace(HomeScreen())
    }

    override suspend fun popBackStack() {
        navigator.back()
    }
}