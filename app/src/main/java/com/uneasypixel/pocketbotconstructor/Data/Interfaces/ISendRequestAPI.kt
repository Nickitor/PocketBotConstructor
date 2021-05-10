package com.uneasypixel.pocketbotconstructor.Data.Interfaces

import org.json.JSONObject
import java.net.URL

interface ISendRequestAPI {

    suspend fun sendRequest(url : URL) : JSONObject?
}