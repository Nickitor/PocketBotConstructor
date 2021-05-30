package com.uneasypixel.pocketbotconstructor.data.api

import com.uneasypixel.pocketbotconstructor.data.interfaces.IGetWallAPI
import com.uneasypixel.pocketbotconstructor.data.interfaces.ISendRequestAPI
import org.json.JSONObject

class GetWallAPIImp(private val sendRequestApi: ISendRequestAPI) : IGetWallAPI {

    override suspend fun getWall(
        groupID: String,
        offset: String,
        count: String,
        token: String
    ): JSONObject? {
        val url = URLBuilder.getUrlGetWall(groupID, offset, count, token)

        var response: JSONObject? = null

        if (url != null)
            response = sendRequestApi.sendRequest(url)

        return response
    }
}