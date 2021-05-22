package com.uneasypixel.pocketbotconstructor.data.api

import com.uneasypixel.pocketbotconstructor.data.interfaces.IGetConversationsAPI
import com.uneasypixel.pocketbotconstructor.data.interfaces.ISendRequestAPI
import org.json.JSONObject

class GetConversationsAPIImp(private val sendRequestApi: ISendRequestAPI) : IGetConversationsAPI {

    override suspend fun getConversations(token: String): JSONObject? {

        val url = URLBuilder.getUrlGetConversations(token)

        var response: JSONObject? = null

        if (url != null)
            response = sendRequestApi.sendRequest(url)

        return response
    }

    override suspend fun getConversationsById(peerIds: List<String>, token: String): JSONObject? {

        val url = URLBuilder.getUrlGetConversationsById(peerIds, token)

        var response: JSONObject? = null

        if (url != null)
            response = sendRequestApi.sendRequest(url)

        return response
    }
}