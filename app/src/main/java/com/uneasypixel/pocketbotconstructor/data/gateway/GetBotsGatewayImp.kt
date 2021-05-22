package com.uneasypixel.pocketbotconstructor.data.gateway

import android.content.Context
import com.google.gson.Gson
import com.uneasypixel.pocketbotconstructor.data.interfaces.IGetBotsStorage
import com.uneasypixel.pocketbotconstructor.domain.entities.Bot
import com.uneasypixel.pocketbotconstructor.domain.interfaces.IGetBotsGateway
import com.uneasypixel.pocketbotconstructor.presentation.dto.BotDTO

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