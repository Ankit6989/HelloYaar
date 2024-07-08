package com.apcoding.helloyaar.common.notification

import com.apcoding.helloyaar.common.Constants
import com.apcoding.helloyaar.data.remote.PushNotification
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/*
interface NotificationApi {

    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification: PushNotification
    ): Response<ResponseBody>
}
*/


interface NotificationApi {
    @Headers("Authorization: Bearer 5da216a345df8e6987b867ae08b8fa12686b5cbc", "Content-Type:application/json")
    @POST("v1/projects/helloyaar-964fa/messages:send")
    suspend fun postNotification(
        @Body notification: PushNotification
    ): Response<ResponseBody>
}


/*
interface NotificationApi {

    @Headers("Authorization: key=${Constants.SERVER_KEY}", "Content-Type:${Constants.CONTENT_TYPE}")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification: PushNotification
    ): Response<ResponseBody>
}

 */
