package com.uneasypixel.pocketbotconstructor.Data.Gateway

import com.uneasypixel.pocketbotconstructor.Data.Interfaces.IGetLongPollServerAPI
import com.uneasypixel.pocketbotconstructor.Domain.DAO.ServerDAO
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Server
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.IGetLongPollServerGateway

class GetLongPollServerGatewayImp(private val getLongPollServerAPI: IGetLongPollServerAPI) : IGetLongPollServerGateway{

    override suspend fun getLongPollServer(groupID: String, token: String): ServerDAO {
        val response = getLongPollServerAPI.getLongPollServer(groupID, token)?.getJSONObject("response")

        return Server(response?.getString("server"), response?.getString("key"), response?.getString("ts"), "25")
    }
}