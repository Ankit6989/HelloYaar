package com.apcoding.helloyaar.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    val userRoomId: Int? = null,
    val userId: String? = null,
    val username: String? = null,
    val userImage: String? = null,
    val token: String? = null,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)