package com.uneasypixel.pocketbotconstructor.Domain.Interfaces

interface IGetConversationsGateway {

    suspend fun getConversations(token : String) : List<String>
}