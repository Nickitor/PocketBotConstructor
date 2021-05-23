package com.uneasypixel.pocketbotconstructor.data.gateway

import com.uneasypixel.pocketbotconstructor.data.interfaces.IGetHistoryAPI
import com.uneasypixel.pocketbotconstructor.domain.entities.Stats
import com.uneasypixel.pocketbotconstructor.domain.interfaces.IGetConversationsGateway
import com.uneasypixel.pocketbotconstructor.domain.interfaces.IGetStatsGateway
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class GetStatsGatewayImp(
    private val getConversationsGateway: IGetConversationsGateway,
    private val getHistoryAPI: IGetHistoryAPI
) : IGetStatsGateway {
    override suspend fun getStats(groupID: String, token: String): Stats {

        val conversationIDs: MutableList<String> = getConversationsGateway.getConversationsIds(token)
        val stats: Stats = Stats()

        if (conversationIDs != null) {

            var startMessageId = ""
            var offset = 0

            for (id in conversationIDs) {

                ++stats.numberOfDialogues

                do {
                    val response: JSONObject? = getHistoryAPI.getHistory(id, startMessageId, offset.toString(), token)

                    println(response)

                    if (response != null && !response.has("error")) {

                        try {
                            val items: JSONArray? =
                                response.getJSONObject("response").getJSONArray("items")

                            if (items!!.length() == 0)
                                break

                            if (startMessageId == "")
                                startMessageId = (items.getJSONObject(0).getString("id"))

                            offset += 200

                            for (item in 0 until items.length()) {
                                val fromId = items.getJSONObject(item).getInt("from_id")

                                if (fromId == id.toInt())
                                    ++stats.numberOfInMessages
                                else
                                    ++stats.numberOfOutMessages
                            }

                        } catch (e: JSONException) {

                        }
                    }
                    else
                        break
                } while (true)
            }
        }
        stats.numberOfMessages = stats.numberOfInMessages + stats.numberOfOutMessages

        return stats
    }
}