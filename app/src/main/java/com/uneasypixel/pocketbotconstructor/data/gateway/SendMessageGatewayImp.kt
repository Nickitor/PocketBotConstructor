package com.uneasypixel.pocketbotconstructor.data.gateway

import com.uneasypixel.pocketbotconstructor.data.interfaces.ISendMessageAPI
import com.uneasypixel.pocketbotconstructor.domain.interfaces.ISendMessageGateway
import org.json.JSONObject

class SendMessageGatewayImp(private val sendMessageApi: ISendMessageAPI) : ISendMessageGateway {

    override suspend fun sendMessageToUser(
        message: String,
        userID: String,
        token: String,
        attachment: String
    ) {
        val response: JSONObject? =
            sendMessageApi.sendMessageToUser(message, userID, token, attachment)
    }
}