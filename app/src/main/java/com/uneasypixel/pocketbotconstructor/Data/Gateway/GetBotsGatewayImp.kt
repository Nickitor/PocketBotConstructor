package com.uneasypixel.pocketbotconstructor.Data.Gateway

import android.content.Context
import com.google.gson.Gson
import com.uneasypixel.pocketbotconstructor.Data.Interfaces.IGetBotsStorage
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.IGetBotsGateway
import com.uneasypixel.pocketbotconstructor.Presentation.DTO.BotDTO

class GetBotsGatewayImp(private val getBotsStorage: IGetBotsStorage) : IGetBotsGateway{

    override fun getBots(context: Context): MutableList<BotDTO> {

        val jsonBots = getBotsStorage.getBots(context)

        val listOfBots : MutableList<BotDTO> = mutableListOf()

        if (jsonBots != null)
            for (botIndex in 0 until jsonBots.length()) {
                val newBot: BotDTO = Gson().fromJson(jsonBots[botIndex].toString(), BotDTO::class.java)
                listOfBots.add(newBot)
            }

        return  listOfBots
    }

    override fun getBot(context: Context, name: String): Bot {
        val jsonBots = getBotsStorage.getBot(context, name)

        return Gson().fromJson(jsonBots.toString(), Bot::class.java)
    }
}