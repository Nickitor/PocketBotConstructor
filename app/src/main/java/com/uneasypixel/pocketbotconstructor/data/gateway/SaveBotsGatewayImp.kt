package com.uneasypixel.pocketbotconstructor.data.gateway

import android.content.Context
import com.uneasypixel.pocketbotconstructor.data.interfaces.ISaveBotsStorage
import com.uneasypixel.pocketbotconstructor.domain.entities.Bot
import com.uneasypixel.pocketbotconstructor.domain.interfaces.ISaveBotsGateway
import com.uneasypixel.pocketbotconstructor.presentation.dto.BotDTO

class SaveBotsGatewayImp(private val saveBotsStorage: ISaveBotsStorage) : ISaveBotsGateway {
    override fun saveBots(context: Context, bots: List<BotDTO>) {
        saveBotsStorage.saveBots(context, bots)
    }

    override fun saveBot(context: Context, bot: Bot) {
        saveBotsStorage.saveBot(context, bot)
    }
}