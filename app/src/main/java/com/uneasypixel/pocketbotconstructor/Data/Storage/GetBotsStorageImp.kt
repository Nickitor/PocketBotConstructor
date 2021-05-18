package com.uneasypixel.pocketbotconstructor.Data.Storage

import android.content.Context
import com.uneasypixel.pocketbotconstructor.Data.Interfaces.IGetBotsStorage
import org.json.JSONArray
import org.json.JSONException

class GetBotsStorageImp : IGetBotsStorage {

    override fun getBots(context: Context): JSONArray? {

        val sharedPreference =  context.getSharedPreferences("Bots", Context.MODE_PRIVATE)
        val listOfBots : String? = sharedPreference.getString("list_of_bots","null")

        try {
            val botsArray = JSONArray(listOfBots)
            return botsArray
        } catch (e : JSONException) {
            return null
        }
    }
}