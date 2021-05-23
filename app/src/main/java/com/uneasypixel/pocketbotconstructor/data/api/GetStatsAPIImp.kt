package com.uneasypixel.pocketbotconstructor.data.api

import com.uneasypixel.pocketbotconstructor.data.interfaces.IGetStatsAPI
import com.uneasypixel.pocketbotconstructor.data.interfaces.ISendRequestAPI
import org.json.JSONObject

class GetStatsAPIImp(private val sendRequestApi: ISendRequestAPI) : IGetStatsAPI {

    override suspend fun getStats(groupID: String, token : String): JSONObject? {
        val url = URLBuilder.getUrlGetStats(groupID, token)

        var response: JSONObject? = null

        if (url != null)
            response = sendRequestApi.sendRequest(url)

        return response
    }
}