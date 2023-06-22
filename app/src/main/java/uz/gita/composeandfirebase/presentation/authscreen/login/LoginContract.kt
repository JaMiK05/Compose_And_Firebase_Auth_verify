package uz.gita.composeandfirebase.presentation.authscreen.login

import org.orbitmvi.orbit.ContainerHost

/**
 *   Created by Jamik on 6/14/2023 ot 5:27 PM
 **/
interface LoginContract {

    interface Model : ContainerHost<UiState, SideEffect> {
        fun eventDispatcher(intent: Intent)
    }

    sealed interface UiState {
        object Default : UiState
    }

    sealed interface Intent {
        data class LogInUser(val email: String, val password: String) : Intent
        object OpenSignUp : Intent
    }

    sealed interface SideEffect {
        data class HasError(val message: String) : SideEffect
    }


}