package com.uneasypixel.pocketbotconstructor.Data.Gateway

import android.content.Context
import com.google.gson.Gson
import com.uneasypixel.pocketbotconstructor.Data.Interfaces.IGetBotsStorage
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.IGetBotsGateway

class GetBotsGatewayImp(private val getBotsStorage: IGetBotsStorage) : IGetBotsGateway{

    override fun getBots(context: Context): MutableList<Bot> {

        val jsonBots = getBotsStorage.getBots(context)

        val listOfBots : MutableList<Bot> = mutableListOf()

        if (jsonBots != null)
            for (botIndex in 0 until jsonBots.length()) {
                val newBot: Bot = Gson().fromJson(jsonBots[botIndex].toString(), Bot::class.java)
                listOfBots.add(newBot)
            }

        return  listOfBots
    }
}