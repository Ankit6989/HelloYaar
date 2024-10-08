package com.apcoding.helloyaar.data.repository

import com.apcoding.helloyaar.data.local.UserDao
import com.apcoding.helloyaar.data.local.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepo @Inject constructor(
    private val dao: UserDao
) {
    val userData: Flow<List<UserEntity>> = dao.allUsersFlow()


    suspend fun addUser(userEntity: UserEntity) {
        dao.insert(userEntity)
    }

    suspend fun deleteUserById(userId: String) {
        dao.deleteUserByUserId(userId)
    }

    fun searchUser(query: String): Flow<List<UserEntity>> {
        return dao.searchUser(query)
    }

    suspend fun userExists(userId: String): Boolean {
        return dao.searchById(userId).isNotEmpty()
    }
}