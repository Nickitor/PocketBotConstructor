package com.uneasypixel.pocketbotconstructor.data.interfaces

import org.json.JSONObject

interface IGetWallAPI {

    suspend fun getWall(groupID: String, offset: String, count: String, token: String): JSONObject?
}