package uz.gita.composeandfirebase.presentation.authscreen.signup

import org.orbitmvi.orbit.ContainerHost

/**
 *   Created by Jamik on 6/14/2023 ot 6:56 PM
 **/
interface SignUpContract {

    interface Model : ContainerHost<UiState, SideEffect> {
        fun eventDispatcher(intent: Intent)
    }

    sealed interface UiState {
        object Default : UiState
    }

    sealed interface Intent {
        data class CreateUser(val email: String, val password: String) : Intent
        object Back : Intent
    }

    sealed interface SideEffect {
        data class HasError(val message: String) : SideEffect
    }

}