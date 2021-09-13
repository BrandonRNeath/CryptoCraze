package com.nemesisprotocol.cryptocraze.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nemesisprotocol.cryptocraze.domain.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}
