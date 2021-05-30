package com.uneasypixel.pocketbotconstructor.domain.interfaces

import org.json.JSONObject

interface IGetWallGateway {
    suspend fun getWall(groupID: String, offset: String, count: String, token: String): JSONObject?
}