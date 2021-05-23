package com.uneasypixel.pocketbotconstructor.domain.interfaces

import com.uneasypixel.pocketbotconstructor.domain.entities.Stats

interface IGetStatsGateway {

    suspend fun getStats(groupID : String, token : String) : Stats
}