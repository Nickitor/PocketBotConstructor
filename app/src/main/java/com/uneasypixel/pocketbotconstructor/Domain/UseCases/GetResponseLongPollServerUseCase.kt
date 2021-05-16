package com.uneasypixel.pocketbotconstructor.Domain.UseCases

import com.uneasypixel.pocketbotconstructor.Domain.DAO.ServerDAO
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.IGetResponseLongPollServerGateway
import org.json.JSONObject

class GetResponseLongPollServerUseCase (private val getResponseLongPollServerGateway: IGetResponseLongPollServerGateway) {

    suspend fun getResponseLongPollServer(server : ServerDAO) : JSONObject? = getResponseLongPollServerGateway.getResponseLongPollServer(server)
}