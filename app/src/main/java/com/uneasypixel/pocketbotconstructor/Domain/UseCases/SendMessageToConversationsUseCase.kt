package com.uneasypixel.pocketbotconstructor.Domain.UseCases

class SendMessageToConversationsUseCase(
    private val getConversationsUseCase: GetConversationsUseCase,
    private val sendMessageToUserUseCase: SendMessageToUserUseCase
) {

    suspend fun sendMessageToCanversations(message : String, token : String) {

        val usersIds : List<String> = getConversationsUseCase.getConversations(token)

        for (user in usersIds)
            sendMessageToUserUseCase.sendMessageToUser(message, user, token)
    }
}