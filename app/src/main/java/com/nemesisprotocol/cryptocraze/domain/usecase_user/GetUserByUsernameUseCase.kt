package com.nemesisprotocol.cryptocraze.domain.usecase_user

import com.nemesisprotocol.cryptocraze.domain.User
import com.nemesisprotocol.cryptocraze.domain.UserRepo
import javax.inject.Inject

class GetUserByUsernameUseCase @Inject constructor(private val userRepo: UserRepo) {
    operator fun invoke(username: String): User = userRepo.getUserByUsername(username)
}
