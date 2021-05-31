package com.uneasypixel.pocketbotconstructor.domain.interfaces

interface ISendMessageGateway {

    suspend fun sendMessageToUser(
        message: String,
        userID: String,
        token: String,
        attachment: String
    )
}