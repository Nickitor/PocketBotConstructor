package com.uneasypixel.pocketbotconstructor.Domain.UseCases

import android.content.Context
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.ISaveBotsGateway

class SaveBotsUseCase(private val saveBotsGateway: ISaveBotsGateway) {

    fun saveBots(context: Context, bots : List<Bot>) {
        saveBotsGateway.saveBots(context, bots)
    }
}