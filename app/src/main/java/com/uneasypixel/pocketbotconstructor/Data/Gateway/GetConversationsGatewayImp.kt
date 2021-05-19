package com.uneasypixel.pocketbotconstructor.Data.Gateway

import com.uneasypixel.pocketbotconstructor.Data.Interfaces.IGetConversationsAPI
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.IGetConversationsGateway
import org.json.JSONArray
import org.json.JSONObject

class GetConversationsGatewayImp(private val getConversationsApi: IGetConversationsAPI) : IGetConversationsGateway {

    override suspend fun getConversations(token : String): List<String> {

        val response : JSONObject? = getConversationsApi.getConversations(token)

        if (response != null) {
            val conversationsArray : JSONArray? = response.getJSONObject("response").getJSONArray("items")

            val result : MutableList<String> = mutableListOf()

            for (i in 0 until conversationsArray!!.length())
                result.add(conversationsArray.getJSONObject(i).getJSONObject("conversation").getJSONObject("peer").getString("id"))

            return result
        }
        else
            return listOf()
    }

}