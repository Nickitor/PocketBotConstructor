package com.uneasypixel.pocketbotconstructor.data.gateway

import com.uneasypixel.pocketbotconstructor.data.interfaces.IGetLongPollServerAPI
import com.uneasypixel.pocketbotconstructor.domain.dao.ServerDAO
import com.uneasypixel.pocketbotconstructor.domain.entities.Server
import com.uneasypixel.pocketbotconstructor.domain.interfaces.IGetLongPollServerGateway

class GetLongPollServerGatewayImp(private val getLongPollServerAPI: IGetLongPollServerAPI) : IGetLongPollServerGateway{

    override suspend fun getLongPollServer(groupID: String, token: String): ServerDAO {
        val response = getLongPollServerAPI.getLongPollServer(groupID, token)?.getJSONObject("response")

        return Server(response?.getString("server"), response?.getString("key"), response?.getString("ts"), "25")
    }
}