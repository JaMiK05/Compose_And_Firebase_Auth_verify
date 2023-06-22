package uz.gita.composeandfirebase.presentation.authscreen.signup

import android.content.Context
import android.widget.Toast
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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
 *   Created by Jamik on 6/14/2023 ot 6:57 PM
 **/
class SignUpModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val direction: SignUpDirection,
) : ScreenModel, SignUpContract.Model {

    override val container =
        coroutineScope.container<SignUpContract.UiState, SignUpContract.SideEffect>(SignUpContract.UiState.Default)
    private val modelScope = CoroutineScope(Dispatchers.Main + Job())

    @Inject
    lateinit var context: Context

    override fun eventDispatcher(intent: SignUpContract.Intent) {
        when (intent) {
            is SignUpContract.Intent.CreateUser -> {
                authRepository.createUser(intent.email, intent.password).onEach {
                    it.onSuccess {
                        myLog("create user sign up succes")
                        authRepository.sendEmailVerification(user!!).onEach { result ->
                            result.onSuccess {
                                Toast.makeText(context, "succes send code", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            result.onFailure {
                                Toast.makeText(context, "fail send code", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }.launchIn(modelScope)
                        //direction.openMainScreen()
                    }
                    it.onFailure {
                        myLog("not create user sign up fail")
                        intent {
                            postSideEffect(
                                SignUpContract.SideEffect.HasError(
                                    it.message ?: "Exception occured!"
                                )
                            )
                        }
                    }
                }.launchIn(modelScope)
            }

            is SignUpContract.Intent.Back -> {
                modelScope.launch {
                    direction.popBackStack()
                }
            }

        }
    }

}