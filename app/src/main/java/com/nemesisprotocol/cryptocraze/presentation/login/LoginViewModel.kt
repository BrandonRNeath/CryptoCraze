package com.nemesisprotocol.cryptocraze.presentation.login

import androidx.lifecycle.ViewModel
import com.nemesisprotocol.cryptocraze.domain.user.User
import com.nemesisprotocol.cryptocraze.domain.user.usecase.AddUserUseCase
import com.nemesisprotocol.cryptocraze.domain.user.usecase.GetUserByUsernameUseCase
import com.nemesisprotocol.cryptocraze.domain.user.usecase.GetUsersUseCase
import com.nemesisprotocol.cryptocraze.domain.user.usecase.IsValidLoginCredentialsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserByUsernameUseCase: GetUserByUsernameUseCase,
    private val getUsersUseCase: GetUsersUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val isValidLoginCredentialsUseCase: IsValidLoginCredentialsUseCase
) : ViewModel() {

    fun createUser(username: String, password: String, confirmPassword: String) {
        addUserUseCase(User(username = username, password = password))
    }

    suspend fun login(username: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val isLoginValid = isValidLoginCredentialsUseCase(username, password)
            isLoginValid
        }
    }
}
