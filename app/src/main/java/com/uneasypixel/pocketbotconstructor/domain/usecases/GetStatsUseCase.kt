package com.uneasypixel.pocketbotconstructor.domain.usecases

import com.uneasypixel.pocketbotconstructor.domain.entities.Stats
import com.uneasypixel.pocketbotconstructor.domain.interfaces.IGetStatsGateway

class GetStatsUseCase(private val getStatsGateway: IGetStatsGateway) {

    suspend fun getStats(groupID: String, token: String): Stats =
        getStatsGateway.getStats(groupID, token)
}