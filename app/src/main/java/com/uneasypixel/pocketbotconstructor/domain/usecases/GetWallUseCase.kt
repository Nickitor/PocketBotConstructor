package com.uneasypixel.pocketbotconstructor.domain.usecases

import com.uneasypixel.pocketbotconstructor.domain.interfaces.IGetWallGateway
import org.json.JSONObject

class GetWallUseCase(private val getWallGateway: IGetWallGateway) {

    suspend fun getWall(
        groupID: String,
        offset: String,
        count: String,
        token: String
    ): JSONObject? = getWallGateway.getWall(groupID, offset, count, token)

}