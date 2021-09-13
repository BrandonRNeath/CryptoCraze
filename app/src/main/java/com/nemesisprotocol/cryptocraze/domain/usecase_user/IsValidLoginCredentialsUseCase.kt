package com.nemesisprotocol.cryptocraze.domain.usecase_user

import com.nemesisprotocol.cryptocraze.domain.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IsValidLoginCredentialsUseCase @Inject constructor(private val userRepo: UserRepo) {
    suspend operator fun invoke(username: String, password: String) =
        withContext(Dispatchers.Default) {
            userRepo.isValidLoginCredentials(username, password)
        }
}
