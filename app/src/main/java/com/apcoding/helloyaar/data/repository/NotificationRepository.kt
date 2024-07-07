package com.apcoding.helloyaar.data.repository


import com.apcoding.helloyaar.common.notification.NotificationApi
import com.apcoding.helloyaar.data.remote.PushNotification
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val api: NotificationApi
) {
    suspend fun sendNotification(notification: PushNotification): Response<ResponseBody> {
        return api.postNotification(notification)
    }
}