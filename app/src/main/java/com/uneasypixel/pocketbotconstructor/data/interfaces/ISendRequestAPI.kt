package com.uneasypixel.pocketbotconstructor.data.interfaces

import org.json.JSONObject
import java.net.URL

interface ISendRequestAPI {

    suspend fun sendRequest(url : URL) : JSONObject?
}