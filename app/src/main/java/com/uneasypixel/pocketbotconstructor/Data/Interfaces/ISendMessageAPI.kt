package com.uneasypixel.pocketbotconstructor.Data.Interfaces

import org.json.JSONObject

interface ISendMessageAPI {

    suspend fun sendMessageToUser(message: String, userID: String, token : String) : JSONObject?
}