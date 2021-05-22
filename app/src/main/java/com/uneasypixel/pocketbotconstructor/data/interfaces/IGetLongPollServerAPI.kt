package com.uneasypixel.pocketbotconstructor.data.interfaces

import org.json.JSONObject

interface IGetLongPollServerAPI {

    suspend fun getLongPollServer(groupID : String, token : String) : JSONObject?
}