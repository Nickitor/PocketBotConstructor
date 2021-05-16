package com.uneasypixel.pocketbotconstructor.Domain.UseCases

import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.ISendMessageGateway

class SendMessageToUserUseCase(private val sendMessage : ISendMessageGateway) {

    suspend fun sendMessageToUser(message: String, userID: String, token : String) {

        return sendMessage.sendMessageToUser(message, userID, token)
    }
}