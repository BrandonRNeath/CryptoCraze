package com.nemesisprotocol.cryptocraze.data.repos

import com.nemesisprotocol.cryptocraze.data.database.user.UserDao
import com.nemesisprotocol.cryptocraze.domain.user.User
import com.nemesisprotocol.cryptocraze.domain.user.UserRepo
import javax.inject.Inject

class UserRepoImpl @Inject constructor(private val userDao: UserDao) : UserRepo {

    override fun getUsers(): List<User> {
        return userDao.getUsers()
    }

    override fun getUserByUsername(username: String): User {
        return userDao.getUserByUsername(username)
    }

    override fun isValidLoginCredentials(username: String, password: String): Boolean {
        return userDao.isValidLoginCredentials(username, password)
    }

    override fun addUser(user: User) {
        userDao.addUser(user)
    }

    override fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    override fun wipeUsers() {
        userDao.wipeUsers()
    }

}
