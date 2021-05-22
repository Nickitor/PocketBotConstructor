package com.uneasypixel.pocketbotconstructor.data.api

import com.uneasypixel.pocketbotconstructor.data.interfaces.IGetLongPollServerAPI
import com.uneasypixel.pocketbotconstructor.data.interfaces.ISendRequestAPI
import org.json.JSONObject

class GetLongPollServerAPIImp(private val sendRequestApi: ISendRequestAPI) : IGetLongPollServerAPI {

    override suspend fun getLongPollServer(groupID: String, token: String): JSONObject? {

        val url = URLBuilder.getURLGetLongPollServer(groupID, token)

        var response: JSONObject? = null

        if (url != null)
            response = sendRequestApi.sendRequest(url)

        return response
    }
}