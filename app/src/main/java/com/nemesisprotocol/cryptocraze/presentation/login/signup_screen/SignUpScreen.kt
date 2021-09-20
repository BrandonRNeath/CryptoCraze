package com.nemesisprotocol.cryptocraze.presentation.login.signup_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nemesisprotocol.cryptocraze.R
import com.nemesisprotocol.cryptocraze.Screen
import com.nemesisprotocol.cryptocraze.presentation.login.LoginViewModel
import com.nemesisprotocol.cryptocraze.presentation.login.components.PasswordTextField
import com.nemesisprotocol.cryptocraze.presentation.login.components.SubmitButton
import com.nemesisprotocol.cryptocraze.presentation.login.components.UsernameTextField

@ExperimentalComposeUiApi
@Composable
fun SignUpScreen(navController: NavHostController, model: LoginViewModel = hiltViewModel()) {
    SignUp(navController) { email: String, password: String, confirmedPassword: String ->
        model.createUser(email, password, confirmedPassword)
    }
}

@ExperimentalComposeUiApi
@Composable
fun SignUp(
    navController: NavHostController,
    onDone: (String, String, String) -> Unit
) {
    val username = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val confirmedPassword = rememberSaveable { mutableStateOf("") }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val (passwordFocusRequester, confirmedPasswordFocusRequester) = FocusRequester.createRefs()
    val keyboardController = LocalSoftwareKeyboardController.current
    val isValid = remember(username.value, password.value, confirmedPassword.value) {
        val usernameIsNotEmpty = username.value.trim().isNotEmpty()
        val passwordIsNotEmpty = password.value.trim().isNotEmpty()
        val confirmedPasswordIsNotEmpty = confirmedPassword.value.trim().isNotEmpty()
        usernameIsNotEmpty && passwordIsNotEmpty && confirmedPasswordIsNotEmpty
    }

    val modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
        .verticalScroll(rememberScrollState())

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.app_name_title),
            modifier = Modifier.padding(vertical = 50.dp, horizontal = 15.dp),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground
        )
        UsernameTextField(
            valueState = username,
            onAction = KeyboardActions {
                passwordFocusRequester.requestFocus()
            }
        )
        PasswordTextField(
            modifier = Modifier.focusRequester(passwordFocusRequester),
            passwordState = password,
            passwordVisibility = passwordVisibility,
            imeAction = ImeAction.Next,
            onAction = KeyboardActions {
                confirmedPasswordFocusRequester.requestFocus()
            }
        )
        PasswordTextField(
            modifier = Modifier.focusRequester(confirmedPasswordFocusRequester),
            passwordState = confirmedPassword,
            labelId = R.string.confirm_password,
            passwordVisibility = passwordVisibility,
            onAction = KeyboardActions {
                if (!isValid) return@KeyboardActions
                onDone(username.value.trim(), password.value.trim(), confirmedPassword.value.trim())
                keyboardController?.hide()
            }
        )
        SubmitButton(
            textId = R.string.sign_up,
            validInputs = isValid
        ) {
            onDone(username.value.trim(), password.value.trim(), confirmedPassword.value.trim())
            keyboardController?.hide()
        }
        Text(
            text = stringResource(id = R.string.have_account_already_sign_in_instead),
            modifier = Modifier
                .clickable {
                    navController.navigate(Screen.Login.route) // onPageChange(LoginViewModel.Page.SIGN_IN)
                }
                .padding(vertical = 20.dp, horizontal = 10.dp),
            color = MaterialTheme.colors.onBackground
        )
    }
}
