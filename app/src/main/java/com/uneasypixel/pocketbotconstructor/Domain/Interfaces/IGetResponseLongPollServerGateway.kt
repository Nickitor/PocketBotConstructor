package com.uneasypixel.pocketbotconstructor.Domain.Interfaces

import com.uneasypixel.pocketbotconstructor.Domain.DAO.ServerDAO
import org.json.JSONObject

interface IGetResponseLongPollServerGateway {

    suspend fun getResponseLongPollServer(server : ServerDAO) : JSONObject?
}