package com.uneasypixel.pocketbotconstructor.Data.Storage

import android.content.Context
import com.google.gson.Gson
import com.uneasypixel.pocketbotconstructor.Data.Interfaces.ISaveBotsStorage
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot

class SaveBotsStorageImp : ISaveBotsStorage {


    override fun saveBots(context: Context, bots: List<Bot>) {

        val outputJson: String = Gson().toJson(bots)

        val sharedPreference =  context.getSharedPreferences("Bots", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("list_of_bots", outputJson)
        editor.apply()
    }
}