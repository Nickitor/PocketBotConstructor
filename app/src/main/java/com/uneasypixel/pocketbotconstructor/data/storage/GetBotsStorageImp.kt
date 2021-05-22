package com.uneasypixel.pocketbotconstructor.data.storage

import android.content.Context
import com.uneasypixel.pocketbotconstructor.data.interfaces.IGetBotsStorage
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class GetBotsStorageImp : IGetBotsStorage {

    override fun getBots(context: Context): JSONArray? {

        val sharedPreference =  context.getSharedPreferences("bots", Context.MODE_PRIVATE)
        val listOfBots : String? = sharedPreference.getString("list_of_bots","null")

        try {
            val botsArray = JSONArray(listOfBots)
            return botsArray
        } catch (e : JSONException) {
            return null
        }
    }

    override fun getBot(context: Context, name: String): JSONObject? {
        val sharedPreference =  context.getSharedPreferences("bots", Context.MODE_PRIVATE)
        val bot : String? = sharedPreference.getString(name,"null")

        try {
            val botJs = JSONObject(bot!!)
            return botJs
        } catch (e : JSONException) {
            return null
        }
    }
}