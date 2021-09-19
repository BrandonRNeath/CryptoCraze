package com.nemesisprotocol.cryptocraze.domain.user.usecase

import com.nemesisprotocol.cryptocraze.domain.user.User
import com.nemesisprotocol.cryptocraze.domain.user.UserRepo
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val userRepo: UserRepo) {
    operator fun invoke(): List<User> = userRepo.getUsers()
}
