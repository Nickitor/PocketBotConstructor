package com.uneasypixel.pocketbotconstructor.data.interfaces

import org.json.JSONObject

interface IGetHistoryAPI {

    suspend fun getHistory(
        peerId: String,
        startMessageId: String,
        offset: String,
        token: String
    ): JSONObject?
}