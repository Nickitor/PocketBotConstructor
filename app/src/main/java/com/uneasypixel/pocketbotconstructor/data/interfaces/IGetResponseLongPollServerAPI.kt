package com.uneasypixel.pocketbotconstructor.data.interfaces

import com.uneasypixel.pocketbotconstructor.domain.dao.ServerDAO
import org.json.JSONObject

interface IGetResponseLongPollServerAPI {

    suspend fun getResponseLongPollServer(server: ServerDAO): JSONObject?
}