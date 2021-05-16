package com.uneasypixel.pocketbotconstructor.Data.Interfaces

import org.json.JSONObject

interface IGetLongPollServerAPI {

    suspend fun getLongPollServer(groupID : String, token : String) : JSONObject?
}