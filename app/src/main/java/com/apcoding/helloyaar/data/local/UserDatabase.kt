package com.apcoding.helloyaar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

class UserDatabase {
    @Database(entities = [UserEntity::class], version = 1)
    abstract class UserDatabase : RoomDatabase() {
        abstract fun userDao(): UserDao
    }
}