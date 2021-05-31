package com.uneasypixel.pocketbotconstructor.domain.usecases

import com.uneasypixel.pocketbotconstructor.domain.interfaces.ISendMessageGateway

class SendMessageToUserUseCase(private val sendMessage: ISendMessageGateway) {

    suspend fun sendMessageToUser(
        message: String,
        userID: String,
        token: String,
        attachment: String = ""
    ) {

        return sendMessage.sendMessageToUser(message, userID, token, attachment)
    }
}