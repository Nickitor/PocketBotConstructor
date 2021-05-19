package com.uneasypixel.pocketbotconstructor.Data.Gateway

import android.content.Context
import com.uneasypixel.pocketbotconstructor.Data.Interfaces.ISaveBotsStorage
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Domain.Interfaces.ISaveBotsGateway

class SaveBotsGatewayImp (private val saveBotsStorage: ISaveBotsStorage) : ISaveBotsGateway {
    override fun saveBots(context: Context, bots: List<Bot>) {
        saveBotsStorage.saveBots(context, bots)
    }
}