package com.uneasypixel.pocketbotconstructor.Data.Interfaces

import android.content.Context
import org.json.JSONArray

interface IGetBotsStorage {

    fun getBots(context: Context) : JSONArray?
}