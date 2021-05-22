package com.uneasypixel.pocketbotconstructor.domain.interfaces

import android.content.Context
import com.uneasypixel.pocketbotconstructor.domain.entities.Bot
import com.uneasypixel.pocketbotconstructor.presentation.dto.BotDTO

interface IGetBotsGateway {

    fun getBots(context: Context) : MutableList<BotDTO>

    fun getBot(context: Context, name : String) : Bot
}