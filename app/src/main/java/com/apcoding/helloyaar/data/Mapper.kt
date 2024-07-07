package com.apcoding.helloyaar.data

import com.apcoding.helloyaar.data.local.UserEntity
import com.apcoding.helloyaar.data.remote.UserData

fun UserData.toUserEntity() = UserEntity(
    username = username, userImage = image, userId = userId, token = token
)

fun UserEntity.toUserData() = UserData(
    userId, username, userImage, token
)