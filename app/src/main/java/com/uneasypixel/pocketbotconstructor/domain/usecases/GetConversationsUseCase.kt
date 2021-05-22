package com.uneasypixel.pocketbotconstructor.domain.usecases

import com.uneasypixel.pocketbotconstructor.domain.entities.Conversation
import com.uneasypixel.pocketbotconstructor.domain.interfaces.IGetConversationsGateway

class GetConversationsUseCase(private val getConversationsGateway: IGetConversationsGateway) {

    suspend fun getConversationsIds(token: String): MutableList<String> = getConversationsGateway.getConversationsIds(token)

    suspend fun getConversationsById(
        peerIds: List<String>,
        token: String
    ): MutableList<Conversation> = getConversationsGateway.getConversationsById(peerIds, token)
}