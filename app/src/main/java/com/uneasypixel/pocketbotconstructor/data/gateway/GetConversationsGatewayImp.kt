package com.uneasypixel.pocketbotconstructor.data.gateway

import com.uneasypixel.pocketbotconstructor.data.interfaces.IGetConversationsAPI
import com.uneasypixel.pocketbotconstructor.domain.entities.Conversation
import com.uneasypixel.pocketbotconstructor.domain.interfaces.IGetConversationsGateway
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class GetConversationsGatewayImp(private val getConversationsApi: IGetConversationsAPI) :
    IGetConversationsGateway {

    override suspend fun getConversationsIds(token: String): MutableList<String> {

        val response: JSONObject? = getConversationsApi.getConversations(token)

        if (response != null && !response.has("error")) {
            val conversationsArray: JSONArray? =
                response.getJSONObject("response").getJSONArray("items")

            val result: MutableList<String> = mutableListOf()

            for (i in 0 until conversationsArray!!.length())
                result.add(
                    conversationsArray.getJSONObject(i).getJSONObject("conversation")
                        .getJSONObject("peer").getString("id")
                )

            return result
        } else
            return mutableListOf()
    }

    override suspend fun getConversationsById(
        peerIds: List<String>,
        token: String
    ): MutableList<Conversation> {
        val response: JSONObject? = getConversationsApi.getConversationsById(peerIds, token)

        if (response != null && !response.has("error")) {
            try {
                val conversationsArray: JSONArray? =
                    response.getJSONObject("response").getJSONArray("items")
                val profilesArray: JSONArray? =
                    response.getJSONObject("response").getJSONArray("profiles")

                val result: MutableList<Conversation> = mutableListOf()

                for (i in 0 until conversationsArray!!.length()) {
                    val conversation = Conversation()

                    conversation.id =
                        conversationsArray.getJSONObject(i).getJSONObject("peer").getString("id")
                    conversation.type =
                        conversationsArray.getJSONObject(i).getJSONObject("peer").getString("type")

                    try {
                        val unread = conversationsArray.getJSONObject(i).getInt("unread_count")
                        conversation.unreadCount = unread
                    } catch (e : JSONException ) {

                    }

                    when (conversation.type) {
                        "user" -> {
                            for (j in 0 until profilesArray!!.length()) {
                                val profileId = profilesArray.getJSONObject(j).getString("id")
                                if (profileId == conversation.id) {
                                    conversation.firstName =
                                        profilesArray.getJSONObject(j).getString("first_name")
                                    conversation.lastName =
                                        profilesArray.getJSONObject(j).getString("last_name")
                                    conversation.photo =
                                        profilesArray.getJSONObject(j).getString("photo_100")
                                    conversation.isOnline =
                                        profilesArray.getJSONObject(j).getJSONObject("online_info")
                                            .getBoolean("is_online")
                                }
                            }
                        }
                        "chat" -> {
                            conversation.title =
                                profilesArray!!.getJSONObject(i).getJSONObject("chat_settings")
                                    .getString("title")
                            conversation.photo =
                                profilesArray!!.getJSONObject(i).getJSONObject("chat_settings")
                                    .getJSONObject("photo").getString("photo_100")
                        }
                    }

                    result.add(conversation)
                }

                return result
            } catch (e: JSONException) {

            }
        }
        return mutableListOf()
    }
}