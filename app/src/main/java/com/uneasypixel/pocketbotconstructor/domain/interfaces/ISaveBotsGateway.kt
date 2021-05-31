package com.uneasypixel.pocketbotconstructor.domain.interfaces

import android.content.Context
import com.uneasypixel.pocketbotconstructor.domain.entities.Bot
import com.uneasypixel.pocketbotconstructor.presentation.dto.BotDTO

interface ISaveBotsGateway {

    fun saveBots(context: Context, bots: List<BotDTO>)

    fun saveBot(context: Context, bot: Bot)
}