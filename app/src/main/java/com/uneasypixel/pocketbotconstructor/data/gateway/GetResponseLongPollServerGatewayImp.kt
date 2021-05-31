package com.uneasypixel.pocketbotconstructor.data.gateway

import com.uneasypixel.pocketbotconstructor.data.interfaces.IGetResponseLongPollServerAPI
import com.uneasypixel.pocketbotconstructor.domain.dao.ServerDAO
import com.uneasypixel.pocketbotconstructor.domain.interfaces.IGetResponseLongPollServerGateway
import org.json.JSONObject

class GetResponseLongPollServerGatewayImp(private val getResponseLongPollServerAPI: IGetResponseLongPollServerAPI) :
    IGetResponseLongPollServerGateway {

    override suspend fun getResponseLongPollServer(server: ServerDAO): JSONObject? =
        getResponseLongPollServerAPI.getResponseLongPollServer(server)
}