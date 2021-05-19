package com.uneasypixel.pocketbotconstructor.Data.Interfaces

import android.content.Context
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Presentation.DTO.BotDTO

interface ISaveBotsStorage {

    fun saveBots(context: Context, bots : List<BotDTO>)

    fun saveBot(context: Context, bot : Bot)
}