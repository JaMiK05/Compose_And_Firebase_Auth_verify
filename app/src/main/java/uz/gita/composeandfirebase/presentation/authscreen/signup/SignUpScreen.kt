package uz.gita.composeandfirebase.presentation.authscreen.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getScreenModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.composeandfirebase.util.myLog

/**
 *   Created by Jamik on 6/14/2023 ot 5:41 PM
 **/
class SignUpScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<SignUpModel>()

        myLog("SignUpScreen -> $this")
        myLog("SignUpScreen screenModel -> $screenModel")

        screenModel.collectSideEffect {
            when (it) {
                is SignUpContract.SideEffect.HasError -> {
                    myLog("Error -> " + it.message)
                }
            }
        }

        val uiState = screenModel.collectAsState().value
        SignUpContent(uiState, screenModel::eventDispatcher)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun SignUpContent(
        uiState: SignUpContract.UiState,
        eventDispatcher: (SignUpContract.Intent) -> Unit,
    ) {


        var emailInput by remember { mutableStateOf("") }
        var passwordInput by remember { mutableStateOf("") }

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Create User", style = MaterialTheme.typography.displayMedium)
                Spacer(modifier = Modifier.padding(4.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = emailInput,
                    onValueChange = {
                        emailInput = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    placeholder = { Text(text = "examle.user@gmail.com", color = Color.Gray) },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Email, contentDescription = null)
                    }

                )
                Spacer(modifier = Modifier.padding(8.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = passwordInput,
                    onValueChange = {
                        passwordInput = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    placeholder = { Text(text = "your password here", color = Color.Gray) },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = null)
                    }


                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 56.dp),
                    onClick = {
                        eventDispatcher(SignUpContract.Intent.CreateUser(emailInput, passwordInput))
                    }) {
                    Text(text = "Sign in")
                }
            }
        }

    }


}