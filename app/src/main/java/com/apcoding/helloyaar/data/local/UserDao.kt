package com.apcoding.helloyaar.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insert(userEntity: UserEntity)

    @Delete
    suspend fun delete(userEntity: UserEntity)

    @Query("DELETE FROM user WHERE userId = :userId")
    suspend fun deleteUserByUserId(userId: String)

    @Query("SELECT * FROM user")
    fun allUsersFlow(): Flow<List<UserEntity>>


    //The query "SELECT * FROM user WHERE username like '%' || :query || '%'" searches for all users whose username contains the given query string.
    //LIKE '%' || :query || '%' is a wildcard search pattern in SQL, where :query is a parameter placeholder. The || operator concatenates the wildcard characters (%) with the query.
    @Query("SELECT * FROM user WHERE username like '%' || :query || '%'")
    fun searchUser(query: String): Flow<List<UserEntity>>


    //The query "SELECT * FROM user WHERE userId like '%' || :query || '%'" searches for all users whose userId contains the given query string.
    //The LIKE pattern is the same as in the first query, facilitating a partial match on userId.
    @Query("SELECT * FROM user WHERE userId like '%' || :query || '%'")
    suspend fun searchById(query: String): List<UserEntity>
}