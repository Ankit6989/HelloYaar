package com.apcoding.helloyaar.data.remote

import com.google.firebase.Timestamp

data class MessageData(
    val messageId: String? = null,
    val message: String? = null,
    var messageIsEmoted: String? = null,
    var replyMessage: String? = null,
    var replyImage: String? = null,
    var replyVideo: String? = null,
    var imageUrl: String? = null,
    var videoUrl: String? = null,

    val senderId: String? = null,
    val senderImage: String? = null,
    val senderUsername: String? = null,
    val senderToken: String? = null,

    val getterId: String? = null,
    val getterImage: String? = null,
    val getterUsername: String? = null,
    val getterToken: String? = null,

    val time: Timestamp? = Timestamp.now(),

    val visibility: MutableList<String> = mutableListOf()
)
