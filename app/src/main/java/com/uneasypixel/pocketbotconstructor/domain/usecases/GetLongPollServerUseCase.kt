package com.uneasypixel.pocketbotconstructor.domain.usecases

import com.uneasypixel.pocketbotconstructor.domain.dao.ServerDAO
import com.uneasypixel.pocketbotconstructor.domain.interfaces.IGetLongPollServerGateway

class GetLongPollServerUseCase(private val getLongPollServerGateway: IGetLongPollServerGateway) {

    suspend fun getLongPollServer(groupID: String, token: String): ServerDAO =
        getLongPollServerGateway.getLongPollServer(groupID, token)
}