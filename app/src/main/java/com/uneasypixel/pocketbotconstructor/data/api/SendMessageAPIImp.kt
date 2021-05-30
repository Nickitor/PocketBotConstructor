package com.uneasypixel.pocketbotconstructor.data.api

import com.uneasypixel.pocketbotconstructor.data.interfaces.ISendMessageAPI
import com.uneasypixel.pocketbotconstructor.data.interfaces.ISendRequestAPI
import org.json.JSONObject

class SendMessageAPIImp(private val sendRequestApi: ISendRequestAPI) : ISendMessageAPI {

    override suspend fun sendMessageToUser(
        message: String,
        userID: String,
        token: String,
        attachment: String
    ): JSONObject? {

        val url = URLBuilder.getUrlSendMessageToID(message, userID, token, attachment)

        var response: JSONObject? = null

        if (url != null)
            response = sendRequestApi.sendRequest(url)

        return response
    }
}