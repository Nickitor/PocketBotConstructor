package com.uneasypixel.pocketbotconstructor.Data.API

import com.uneasypixel.pocketbotconstructor.Data.Interfaces.IGetConversationsAPI
import com.uneasypixel.pocketbotconstructor.Data.Interfaces.ISendRequestAPI
import org.json.JSONObject

class GetConversationsAPIImp(private val sendRequestApi: ISendRequestAPI) : IGetConversationsAPI {

    override suspend fun getConversations(token : String): JSONObject? {

        val url = URLBuilder.getUrlGetConversations(token)

        var response: JSONObject? = null

        if (url != null)
            response = sendRequestApi.sendRequest(url)

        return response
    }
}