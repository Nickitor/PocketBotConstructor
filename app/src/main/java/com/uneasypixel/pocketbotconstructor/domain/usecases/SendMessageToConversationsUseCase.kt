package com.uneasypixel.pocketbotconstructor.domain.usecases

class SendMessageToConversationsUseCase(
    private val getConversationsUseCase: GetConversationsUseCase,
    private val sendMessageToUserUseCase: SendMessageToUserUseCase
) {

    suspend fun sendMessageToConversations(message : String, token : String) {

        val usersIds : List<String> = getConversationsUseCase.getConversationsIds(token)

        for (user in usersIds)
            sendMessageToUserUseCase.sendMessageToUser(message, user, token)
    }
}