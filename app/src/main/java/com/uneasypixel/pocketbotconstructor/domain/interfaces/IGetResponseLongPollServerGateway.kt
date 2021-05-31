package com.uneasypixel.pocketbotconstructor.domain.interfaces

import com.uneasypixel.pocketbotconstructor.domain.dao.ServerDAO
import org.json.JSONObject

interface IGetResponseLongPollServerGateway {

    suspend fun getResponseLongPollServer(server: ServerDAO): JSONObject?
}