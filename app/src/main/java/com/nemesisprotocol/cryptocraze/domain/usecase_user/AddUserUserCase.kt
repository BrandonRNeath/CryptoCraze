package com.nemesisprotocol.cryptocraze.domain.usecase_user

import com.nemesisprotocol.cryptocraze.domain.User
import com.nemesisprotocol.cryptocraze.domain.UserRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddUserUserCase @Inject constructor(private val userRepo: UserRepo) {
    private val addUserCoroutineScope = CoroutineScope(Dispatchers.Default)
    operator fun invoke(user: User) = addUserCoroutineScope.launch { userRepo.addUser(user) }
}
