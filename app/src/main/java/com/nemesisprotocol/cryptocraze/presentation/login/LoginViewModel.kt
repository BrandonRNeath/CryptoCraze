package com.nemesisprotocol.cryptocraze.presentation.login

import androidx.lifecycle.ViewModel
import com.nemesisprotocol.cryptocraze.domain.User
import com.nemesisprotocol.cryptocraze.domain.usecase_user.AddUserUserCase
import com.nemesisprotocol.cryptocraze.domain.usecase_user.GetUserByUsernameUseCase
import com.nemesisprotocol.cryptocraze.domain.usecase_user.GetUsersUseCase
import com.nemesisprotocol.cryptocraze.domain.usecase_user.IsValidLoginCredentialsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserByUsernameUseCase: GetUserByUsernameUseCase,
    private val getUsersUseCase: GetUsersUseCase,
    private val addUserUserCase: AddUserUserCase,
    private val isValidLoginCredentialsUseCase: IsValidLoginCredentialsUseCase
) : ViewModel() {

    fun createUser(username: String, password: String, confirmPassword: String) {
        addUserUserCase(User(username = username, password = password))
    }

    suspend fun login(username: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val isLoginValid = isValidLoginCredentialsUseCase(username, password)
            isLoginValid
        }
    }
}
