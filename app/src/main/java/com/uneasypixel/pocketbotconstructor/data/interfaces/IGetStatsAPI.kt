package com.uneasypixel.pocketbotconstructor.data.interfaces

import org.json.JSONObject

interface IGetStatsAPI {

    suspend fun getStats(groupID: String, token: String): JSONObject?
}