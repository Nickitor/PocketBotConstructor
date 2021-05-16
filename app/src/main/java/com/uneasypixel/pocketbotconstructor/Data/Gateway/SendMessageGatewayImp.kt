package com.uneasypixel.pocketbotconstructor.Data.Gateway

import com.uneasypixel.pocketbotconstructor.Data.Interfaces.ISendMessageAPI
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.ISendMessageGateway
import org.json.JSONObject

class SendMessageGatewayImp(private val sendMessageApi : ISendMessageAPI) : ISendMessageGateway {

    override suspend fun sendMessageToUser(message: String, userID: String, token : String) {
        val response : JSONObject? = sendMessageApi.sendMessageToUser(message, userID, token)
    }
}