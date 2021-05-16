package com.uneasypixel.pocketbotconstructor.Domain.Interfaces

import com.uneasypixel.pocketbotconstructor.Domain.DAO.ServerDAO

interface IGetLongPollServerGateway {

    suspend fun getLongPollServer(groupID : String, token : String) : ServerDAO
}