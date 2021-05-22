package com.uneasypixel.pocketbotconstructor.domain.usecases

import android.content.Context
import com.uneasypixel.pocketbotconstructor.domain.entities.Bot
import com.uneasypixel.pocketbotconstructor.domain.interfaces.ISaveBotsGateway
import com.uneasypixel.pocketbotconstructor.presentation.dto.BotDTO

class SaveBotsUseCase(private val saveBotsGateway: ISaveBotsGateway) {

    fun saveBots(context: Context, bots : List<BotDTO>) {
        saveBotsGateway.saveBots(context, bots)
    }

    fun saveBot(context: Context, bot : Bot) {
        saveBotsGateway.saveBot(context, bot)
    }
}