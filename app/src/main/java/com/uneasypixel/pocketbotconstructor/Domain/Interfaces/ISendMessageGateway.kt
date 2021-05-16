package com.uneasypixel.pocketbotconstructor.Domain.Interfaces

interface ISendMessageGateway {

    suspend fun sendMessageToUser(message: String, userID: String, token : String)
}