package com.uneasypixel.pocketbotconstructor.data.interfaces

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

interface IGetBotsStorage {

    fun getBots(context: Context) : JSONArray?

    fun getBot(context: Context, name : String) : JSONObject?
}