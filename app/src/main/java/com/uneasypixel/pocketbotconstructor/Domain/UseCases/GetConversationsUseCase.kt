package com.uneasypixel.pocketbotconstructor.Domain.UseCases

import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.IGetConversationsGateway

class GetConversationsUseCase(private val getConversationsGateway: IGetConversationsGateway) {

    suspend fun getConversations(token : String) : List<String> = getConversationsGateway.getConversations(token)
}