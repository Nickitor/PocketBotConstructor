package com.uneasypixel.pocketbotconstructor.Data.Interfaces

import com.uneasypixel.pocketbotconstructor.Domain.DAO.ServerDAO
import org.json.JSONObject

interface IGetResponseLongPollServerAPI {

    suspend fun getResponseLongPollServer(server : ServerDAO) : JSONObject?
}