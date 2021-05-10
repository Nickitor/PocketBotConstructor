package com.uneasypixel.pocketbotconstructor.Domain.Interfaces

import com.uneasypixel.pocketbotconstructor.Data.DTO.ResponseDTO

interface ISendMessageGateway {

    suspend fun sendMessageToUser(message: String, userID: String, token : String) : ResponseDTO
}