package uz.gita.composeandfirebase.presentation.authscreen.login

import android.content.Context
import android.widget.Toast
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import uz.gita.composeandfirebase.domain.repositories.authrepository.AuthRepository
import uz.gita.composeandfirebase.domain.repositories.authrepository.impl.user
import uz.gita.composeandfirebase.util.myLog
import javax.inject.Inject

/**
 *   Created by Jamik on 6/14/2023 ot 5:42 PM
 **/
class LoginModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val direction: LoginDirection,
) : ScreenModel, LoginContract.Model {

    override val container =
        coroutineScope.container<LoginContract.UiState, LoginContract.SideEffect>(LoginContract.UiState.Default)

    private val modelScope = CoroutineScope(Dispatchers.Main + Job())

    @Inject
    lateinit var context: Context

    override fun eventDispatcher(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.LogInUser -> {
                myLog("ViewModel -> LogInUser")
                authRepository.logIn(intent.email, intent.password).onEach {
                    it.onSuccess {
                        myLog("success login ")
                        Toast.makeText(context, user?.email, Toast.LENGTH_SHORT).show()
                    }
                    it.onFailure {
                        intent {
                            myLog(it.message ?: "Exception occured!")
                            postSideEffect(
                                LoginContract.SideEffect.HasError(
                                    it.message ?: "Exception occured!"
                                )
                            )
                        }
                    }
                }.launchIn(modelScope)
            }

            is LoginContract.Intent.OpenSignUp -> {
                modelScope.launch {
                    direction.openSignUpScreen()
                }
            }
        }
    }


}