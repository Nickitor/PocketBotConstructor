package com.uneasypixel.pocketbotconstructor.data.api

import com.uneasypixel.pocketbotconstructor.data.interfaces.IGetHistoryAPI
import com.uneasypixel.pocketbotconstructor.data.interfaces.ISendRequestAPI
import org.json.JSONObject

class GetHistoryAPIImp(private val sendRequestApi: ISendRequestAPI) : IGetHistoryAPI {
    override suspend fun getHistory(
        peerId: String,
        startMessageId: String,
        offset: String,
        token: String
    ): JSONObject? {
        val url = URLBuilder.getUrlGetHistory(peerId, startMessageId, offset, token)

        var response: JSONObject? = null

        if (url != null)
            response = sendRequestApi.sendRequest(url)

        return response
    }
}