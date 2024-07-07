package com.apcoding.helloyaar.presentation.screens.viewmodel

import android.content.ContentResolver
import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apcoding.helloyaar.data.remote.MessageData
import com.apcoding.helloyaar.data.remote.PushNotification
import com.apcoding.helloyaar.data.remote.UserData
import com.apcoding.helloyaar.data.repository.MessageRepo
import com.apcoding.helloyaar.data.repository.NotificationRepository
import com.apcoding.helloyaar.data.repository.RoomRepo
import com.apcoding.helloyaar.data.repository.UserRepo
import com.apcoding.helloyaar.data.toUserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MessageViewModel @Inject constructor(
    private val messageRepo: MessageRepo,
    userRepo: UserRepo,
    private val notificationRepo: NotificationRepository,
    private val roomRepo: RoomRepo
) : ViewModel(){


    val userData = userRepo.userData.value

    private val users = userRepo.usersData
    val privateMessagesData = messageRepo.privateMessagesData
    val allMessages = messageRepo.allMessages
    val selectedMessages = mutableStateListOf<MessageData>()


    var replyingPerson = mutableStateOf("")
    var replyingMessage = mutableStateOf("")
    var replyingImage = mutableStateOf("")
    var replyingVideo = mutableStateOf("")
    var isReplyingState = mutableStateOf(false)

    val isMediaLoading = messageRepo.isMediaLoading

    fun sendMedia(
        uri: Uri,
        messageData: MessageData,
        contentResolver: ContentResolver
    ) {
        val user = users.value.find { it.userId == messageData.getterId } ?: UserData()

        messageRepo.sendMedia(
            uri = uri,
            messageData = messageData,
            contentResolver = contentResolver,
            onSuccess = {
                viewModelScope.launch {
                    val userExisted = roomRepo.userExists(user.userId ?: "")
                    if (!userExisted){
                        roomRepo.addUser(
                            user.toUserEntity()
                        )
                    }
                }
            }
        )
    }


    fun sendMessage(
        messageData: MessageData
    ) {
        val user = users.value.find { it.userId == messageData.getterId } ?: UserData()

        messageRepo.sendMessage(
            messageData = messageData,
            onSuccess = {
                viewModelScope.launch {
                    val userExisted = roomRepo.userExists(user.userId ?: "")
                    if (!userExisted){
                        roomRepo.addUser(
                            user.toUserEntity()
                        )
                    }
                }
            }
        )
    }


    fun getPrivateMessages(senderId: String, getterId: String) {
        messageRepo.getPrivateMessages(senderId, getterId)
    }

    fun deleteMessageFromDatabase(messageId: String) {
        messageRepo.deleteMessageFromDatabase(messageId)
    }

    fun deleteMessage(messageData: MessageData) {
        messageRepo.deleteMessage(messageData)
    }


    fun emoteMessage(messageId: String, myEmoji: String) {
        messageRepo.emoteMessage(messageId, myEmoji)
    }

    suspend fun sendNotificiation(notification: PushNotification){
        notificationRepo.sendNotification(notification)
    }

}