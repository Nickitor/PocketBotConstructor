package com.uneasypixel.pocketbotconstructor.data.interfaces

import org.json.JSONObject

interface IGetConversationsAPI {

    suspend fun getConversations(token: String): JSONObject?

    suspend fun getConversationsById(peerIds: List<String>, token: String): JSONObject?
}