package com.uneasypixel.pocketbotconstructor.Domain.UseCases

import android.content.Context
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.ISaveBotsGateway
import com.uneasypixel.pocketbotconstructor.Presentation.DTO.BotDTO

class SaveBotsUseCase(private val saveBotsGateway: ISaveBotsGateway) {

    fun saveBots(context: Context, bots : List<BotDTO>) {
        saveBotsGateway.saveBots(context, bots)
    }

    fun saveBot(context: Context, bot : Bot) {
        saveBotsGateway.saveBot(context, bot)
    }
}