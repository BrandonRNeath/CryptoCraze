package com.nemesisprotocol.cryptocraze.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nemesisprotocol.cryptocraze.domain.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUsers(): List<User>

    @Query("SELECT * from user where username = :username")
    fun getUserByUsername(username: String): User

    @Query("SELECT EXISTS(SELECT * FROM user WHERE username = :username AND password = :password)")
    fun isValidLoginCredentials(username: String, password: String) : Boolean

    @Insert
    fun addUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM user")
    fun wipeUsers()

}