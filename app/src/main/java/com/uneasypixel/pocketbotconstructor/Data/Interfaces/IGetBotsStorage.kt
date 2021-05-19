package com.uneasypixel.pocketbotconstructor.Data.Interfaces

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

interface IGetBotsStorage {

    fun getBots(context: Context) : JSONArray?

    fun getBot(context: Context, name : String) : JSONObject?
}