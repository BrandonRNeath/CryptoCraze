package com.nemesisprotocol.cryptocraze.domain

interface UserRepo {

    fun getUsers(): List<User>

    fun getUserByUsername(username: String): User

    fun isValidLoginCredentials(username: String, password: String) : Boolean

    fun addUser(user: User)

    fun deleteUser(user: User)

    fun wipeUsers()
}
