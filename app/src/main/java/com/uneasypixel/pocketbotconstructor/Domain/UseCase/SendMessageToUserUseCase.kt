package com.uneasypixel.pocketbotconstructor.Domain.UseCase

import com.uneasypixel.pocketbotconstructor.Domain.DAO.ResponseDAO
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.ISendMessageGateway

class SendMessageToUserUseCase(private val sendMessage : ISendMessageGateway) {

    suspend fun sendMessageToUser(message: String, userID: String, token : String) : ResponseDAO {

        return sendMessage.sendMessageToUser(message, userID, token)
    }
}