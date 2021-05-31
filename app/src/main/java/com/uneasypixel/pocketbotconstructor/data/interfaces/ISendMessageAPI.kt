package com.uneasypixel.pocketbotconstructor.data.interfaces

import org.json.JSONObject

interface ISendMessageAPI {

    suspend fun sendMessageToUser(
        message: String,
        userID: String,
        token: String,
        attachment: String
    ): JSONObject?
}