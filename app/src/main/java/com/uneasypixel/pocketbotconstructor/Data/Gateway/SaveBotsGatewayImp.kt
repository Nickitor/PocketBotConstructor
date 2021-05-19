package com.uneasypixel.pocketbotconstructor.Data.Gateway

import android.content.Context
import com.uneasypixel.pocketbotconstructor.Data.Interfaces.ISaveBotsStorage
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.ISaveBotsGateway
import com.uneasypixel.pocketbotconstructor.Presentation.DTO.BotDTO

class SaveBotsGatewayImp (private val saveBotsStorage: ISaveBotsStorage) : ISaveBotsGateway {
    override fun saveBots(context: Context, bots: List<BotDTO>) {
        saveBotsStorage.saveBots(context, bots)
    }

    override fun saveBot(context: Context, bot: Bot) {
        saveBotsStorage.saveBot(context, bot)
    }
}