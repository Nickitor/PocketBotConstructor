package com.uneasypixel.pocketbotconstructor.data.interfaces

import android.content.Context
import com.uneasypixel.pocketbotconstructor.domain.entities.Bot
import com.uneasypixel.pocketbotconstructor.presentation.dto.BotDTO

interface ISaveBotsStorage {

    fun saveBots(context: Context, bots: List<BotDTO>)

    fun saveBot(context: Context, bot: Bot)
}