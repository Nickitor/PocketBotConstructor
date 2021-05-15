package com.uneasypixel.pocketbotconstructor.Data.Interfaces

import org.json.JSONObject

interface IGetConversationsAPI {

    suspend fun getConversations(token : String) : JSONObject?
}