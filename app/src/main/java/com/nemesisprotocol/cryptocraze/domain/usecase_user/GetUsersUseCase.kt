package com.nemesisprotocol.cryptocraze.domain.usecase_user

import com.nemesisprotocol.cryptocraze.domain.User
import com.nemesisprotocol.cryptocraze.domain.UserRepo
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val userRepo: UserRepo) {
    operator fun invoke(): List<User> = userRepo.getUsers()
}
