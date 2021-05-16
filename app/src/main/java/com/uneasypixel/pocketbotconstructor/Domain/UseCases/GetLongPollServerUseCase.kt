package com.uneasypixel.pocketbotconstructor.Domain.UseCases

import com.uneasypixel.pocketbotconstructor.Domain.DAO.ServerDAO
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.IGetLongPollServerGateway

class GetLongPollServerUseCase(private val getLongPollServerGateway: IGetLongPollServerGateway) {

    suspend fun getLongPollServer(groupID : String, token : String) : ServerDAO = getLongPollServerGateway.getLongPollServer(groupID, token)
}