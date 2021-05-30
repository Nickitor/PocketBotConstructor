package com.uneasypixel.pocketbotconstructor.data.gateway

import com.uneasypixel.pocketbotconstructor.data.interfaces.IGetWallAPI
import com.uneasypixel.pocketbotconstructor.domain.interfaces.IGetWallGateway
import org.json.JSONObject

class GetWallGatewayImp(private val getWallAPI: IGetWallAPI) : IGetWallGateway {
    override suspend fun getWall(
        groupID: String,
        offset: String,
        count: String,
        token: String
    ): JSONObject? = getWallAPI.getWall(groupID, offset, count, token)
}