package com.uneasypixel.pocketbotconstructor.domain.interfaces

import com.uneasypixel.pocketbotconstructor.domain.dao.ServerDAO

interface IGetLongPollServerGateway {

    suspend fun getLongPollServer(groupID: String, token: String): ServerDAO
}