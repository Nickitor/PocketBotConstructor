package com.uneasypixel.pocketbotconstructor.Data.Gateway

import com.uneasypixel.pocketbotconstructor.Data.Interfaces.IGetResponseLongPollServerAPI
import com.uneasypixel.pocketbotconstructor.Domain.DAO.ServerDAO
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.IGetResponseLongPollServerGateway
import org.json.JSONObject

class GetResponseLongPollServerGatewayImp(private val getResponseLongPollServerAPI: IGetResponseLongPollServerAPI) : IGetResponseLongPollServerGateway {

    override suspend fun getResponseLongPollServer(server: ServerDAO): JSONObject? = getResponseLongPollServerAPI.getResponseLongPollServer(server)
}