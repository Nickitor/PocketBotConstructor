package com.uneasypixel.pocketbotconstructor.Data.API

import com.uneasypixel.pocketbotconstructor.Data.Interfaces.ISendMessageAPI
import com.uneasypixel.pocketbotconstructor.Data.Interfaces.ISendRequestAPI
import org.json.JSONObject

class SendMessageAPIImp(private val sendRequestApi: ISendRequestAPI) : ISendMessageAPI {

    override suspend fun sendMessageToUser(
        message: String,
        userID: String,
        token: String
    ): JSONObject? {

        val url = URLBuilder.getUrlSendMessageToID(message, userID, token)

        var response: JSONObject? = null

        if (url != null)
            response = sendRequestApi.sendRequest(url)

        return response
    }
}