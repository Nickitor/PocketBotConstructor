package com.uneasypixel.pocketbotconstructor.domain.interfaces

import com.uneasypixel.pocketbotconstructor.domain.entities.Conversation

interface IGetConversationsGateway {

    suspend fun getConversationsIds(token: String): MutableList<String>

    suspend fun getConversationsById(
        peerIds: List<String>,
        token: String
    ): MutableList<Conversation>
}