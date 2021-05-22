package com.uneasypixel.pocketbotconstructor.domain.usecases

import com.uneasypixel.pocketbotconstructor.domain.dao.ServerDAO
import com.uneasypixel.pocketbotconstructor.domain.interfaces.IGetResponseLongPollServerGateway
import org.json.JSONObject

class GetResponseLongPollServerUseCase (private val getResponseLongPollServerGateway: IGetResponseLongPollServerGateway) {

    suspend fun getResponseLongPollServer(server : ServerDAO) : JSONObject? = getResponseLongPollServerGateway.getResponseLongPollServer(server)
}