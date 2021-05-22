package com.uneasypixel.pocketbotconstructor.data.storage

import android.content.Context
import com.google.gson.Gson
import com.uneasypixel.pocketbotconstructor.data.interfaces.ISaveBotsStorage
import com.uneasypixel.pocketbotconstructor.domain.entities.Bot
import com.uneasypixel.pocketbotconstructor.presentation.dto.BotDTO

class SaveBotsStorageImp : ISaveBotsStorage {


    override fun saveBots(context: Context, bots: List<BotDTO>) {

        val outputJson: String = Gson().toJson(bots)

        val sharedPreference =  context.getSharedPreferences("bots", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("list_of_bots", outputJson)
        editor.apply()
    }

    override fun saveBot(context: Context, bot: Bot) {
        val outputJson: String = Gson().toJson(bot)

        val sharedPreference =  context.getSharedPreferences("bots", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString(bot.name, outputJson)
        editor.apply()
    }
}