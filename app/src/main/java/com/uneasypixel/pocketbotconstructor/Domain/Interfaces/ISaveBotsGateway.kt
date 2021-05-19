package com.uneasypixel.pocketbotconstructor.Domain.Interfaces

import android.content.Context
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Presentation.DTO.BotDTO

interface ISaveBotsGateway {

    fun saveBots(context: Context, bots : List<BotDTO>)

    fun saveBot(context: Context, bot : Bot)
}